/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.impl;

import static java.util.Arrays.asList;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DefaultDataType.getDataType;
import static org.jooq.impl.SQLDataType.VARCHAR;
import static org.jooq.impl.Tools.fields;
import static org.jooq.impl.Tools.newRecord;
import static org.jooq.tools.StringUtils.defaultIfBlank;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.tools.json.ContainerFactory;
import org.jooq.tools.json.JSONParser;

/**
 * A very simple JSON reader based on Simple JSON.
 *
 * @author Johannes Bühler
 * @author Lukas Eder
 */
@SuppressWarnings({ "unchecked" })
final class JSONReader<R extends Record> {

    private final DSLContext         ctx;
    private final AbstractRow<R>     row;
    private final Class<? extends R> recordType;

    JSONReader(DSLContext ctx, AbstractRow<R> row, Class<? extends R> recordType) {
        this.ctx = ctx;
        this.row = row;
        this.recordType = recordType != null ? recordType : (Class<? extends R>) Record.class;
    }

    final Result<R> read(String string) {
        return read(new StringReader(string));
    }

    @SuppressWarnings("rawtypes")
    final Result<R> read(final Reader reader) {
        try {
            Object root = new JSONParser().parse(reader, new ContainerFactory() {
                @Override
                public Map createObjectContainer() {
                    return new LinkedHashMap();
                }

                @Override
                public List createArrayContainer() {
                    return new ArrayList();
                }
            });

            AbstractRow<R> actualRow = row;
            List<Field<?>> header = new ArrayList<>();

            List<?> records;
            Result<R> result = null;

            if (root instanceof Map) {
                Map<String, Object> o1 = (Map<String, Object>) root;
                List<Map<String, String>> fields = (List<Map<String, String>>) o1.get("fields");

                if (fields != null) {
                    for (Map<String, String> field : fields) {
                        String catalog = field.get("catalog");
                        String schema = field.get("schema");
                        String table = field.get("table");
                        String name = field.get("name");
                        String type = field.get("type");

                        header.add(field(name(catalog, schema, table, name), getDataType(ctx.dialect(), defaultIfBlank(type, "VARCHAR"))));
                    }
                }

                records = (List<?>) o1.get("records");
            }
            else
                records = (List<?>) root;

            if (actualRow == null && !header.isEmpty())
                actualRow = (AbstractRow<R>) Tools.row0(header);

            if (actualRow != null)
                result = new ResultImpl<>(ctx.configuration(), actualRow);

            for (Object o3 : records) {
                if (o3 instanceof Map) {
                    Map<String, Object> record = (Map<String, Object>) o3;

                    if (result == null) {
                        if (header.isEmpty())
                            for (String name : record.keySet())
                                header.add(field(name(name), VARCHAR));

                        result = new ResultImpl<>(ctx.configuration(), actualRow = (AbstractRow<R>) Tools.row0(header));
                    }

                    result.add(newRecord(true, recordType, actualRow, ctx.configuration()).operate(r -> {
                        r.fromMap(record);
                        return r;
                    }));
                }
                else {
                    List record = (List) o3;

                    if (result == null) {
                        if (header.isEmpty())
                            header.addAll(asList(fields(record.size())));

                        result = new ResultImpl<>(ctx.configuration(), actualRow = (AbstractRow<R>) Tools.row0(header));
                    }

                    // [#8829] LoaderImpl expects binary data to be encoded in base64,
                    //         not according to org.jooq.tools.Convert
                    for (int i = 0; i < result.fields().length; i++)
                        if (result.field(i).getType() == byte[].class && record.get(i) instanceof String)
                            record.set(i, DatatypeConverter.parseBase64Binary((String) record.get(i)));

                    result.add(newRecord(true, recordType, actualRow, ctx.configuration()).operate(r -> {
                        r.from(record);
                        return r;
                    }));
                }
            }

            return result;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

