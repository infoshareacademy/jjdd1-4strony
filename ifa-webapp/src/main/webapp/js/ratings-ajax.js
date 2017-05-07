$(document).ready(function () {
    $.ajax({
        url: '/resources/ratings/json/' + $('#fund-id').text(),
        dataType: 'json',
        success: function (response) {
            var chart = AmCharts.makeChart("chart-container", {
                type: "stock",
                "theme": "light",

                "dataSets": [
                    {
                        "fieldMappings": [{
                            "fromField": "close",
                            "toField": "close"
                        }
                        ],
                        "dataProvider": response,
                        "categoryField": "date",
                        "compared": false
                    }
                ],

                "panels": [{
                    recalculateToPercents: "never",

                    "valueAxes": [
                        {"id": "axis1"},
                        {"id": "axis2"}
                    ],

                    "stockGraphs": [
                        {
                            "id": "g1",
                            "title": "Graph #1",
                            "lineThickness": 2,
                            "valueField": "close",
                            "useDataSetColors": false,
                            "valueAxis": "axis1",
                        }
                    ]

                }],

                "chartScrollbarSettings": {
                    "graph": "g1"
                },

                "chartCursorSettings": {
                    "valueBalloonsEnabled": true,
                    "fullWidth": true,
                    "cursorAlpha": 0.1,
                    "valueLineBalloonEnabled": true,
                    "valueLineEnabled": true,
                    "valueLineAlpha": 0.5
                },

                "periodSelector": {
                    "position": "bottom",
                    "periods": [{
                        "period": "MM",
                        "count": 1,
                        "label": "1 month"
                    }, {
                        "period": "YYYY",
                        "count": 1,
                        "label": "1 year"
                    }, {
                        "period": "YTD",
                        "label": "YTD"
                    }, {
                        "period": "MAX",
                        "selected": true,
                        "label": "MAX"
                    }]
                }
            });
        },
        error: function (response, status, er) {
            alert("error: " + response + " status: " + status + " er:" + er);
        }
    });
});