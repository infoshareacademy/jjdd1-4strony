package com.isacademy.jjdd1.czterystrony.reports.mail;

import java.util.List;

public class HtmlTableBuilder {
    private final String HTML_START = "<html>" + setHead() + "<body><table>";
    private final String HTML_END = "</table></body></html>";
    private String headers;
    private String body = "";

    public HtmlTableBuilder setHeaders(List<String> headers) {
        StringBuilder sb = new StringBuilder();
        headers.forEach(h -> sb.append("<th>" + h + "</th>"));
        this.headers = createRow(sb.toString());
        return this;
    }

    public HtmlTableBuilder setRow(List<String> row) {
        StringBuilder sb = new StringBuilder();
        row.forEach(c -> sb.append("<td>" + c + "</td>"));
        this.body += createRow(sb.toString());
        return this;
    }

    public String build() {
        return HTML_START + headers + body + HTML_END;
    }

    private String createRow(String columns) {
        return "<tr>" + columns + "</tr>";
    }

    private String setHead() {
        return "<head>" +
                "<style>" +
                "table {" +
                "font-family: arial, sans-serif;" +
                "border-collapse: collapse;" +
                "width: 100%;" +
                "}" +
                "td, th {" +
                "border: 1px solid #dddddd;" +
                "text-align: left;" +
                "padding: 8px;" +
                "}" +
                "tr:nth-child(even) {" +
                "background-color: #dddddd;" +
                "}" +
                "</style>" +
                "</head>";
    }
}
