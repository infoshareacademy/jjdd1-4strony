$(document).ready(function () {
    $.ajax({
        url: '/resources/ratings/all/json/' + $('#fund-id').text(),
        dataType: 'json',
        success: function (response) {
            var chart = AmCharts.makeChart("chart-container", {
                type: "stock",
                "theme": "light",

                "dataSets": [{
                    "fieldMappings": [{
                        "fromField": "close",
                        "toField": "close"
                    }],
                    "dataProvider": response,
                    "categoryField": "date",
                    "compared": false
                }],

                "panels": [{
                    recalculateToPercents: "never",

                    "title": "wartość j.u. netto w PLN",

                    "valueAxes": [{
                        "id": "axis1",
                        "minimum": 0
                    }],

                    "stockGraphs": [{
                        "id": "g1",
                        "title": $('#fund-id').text(),
                        "lineThickness": 2,
                        "valueField": "close",
                        "useDataSetColors": false,
                        "valueAxis": "axis1"
                    }],

                    "stockLegend": {
                        "valueTextRegular": " ",
                        "markerType": "none"
                    }

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
                    "periods": [
                        {
                            "period": "DD",
                            "count": 7,
                            "label": "7 days"
                        }, {
                            "period": "MM",
                            "count": 1,
                            "label": "1 month"
                        }, {
                            "period": "MM",
                            "count": 3,
                            "label": "3 months"
                        }, {
                            "period": "MM",
                            "count": 6,
                            "label": "6 months"
                        }, {
                            "period": "YYYY",
                            "count": 1,
                            "label": "1 year"
                        }, {
                            "period": "YYYY",
                            "count": 3,
                            "label": "3 years"
                        },{
                            "period": "YTD",
                            "label": "YTD"
                        }, {
                            "period": "MAX",
                            "selected": true,
                            "label": "MAX"
                        }
                    ]
                },

                "panelsSettings": {
                    "usePrefixes": true
                },
                "export": {
                    "enabled": true
                }
            });
        },
        error: function (response, status, er) {
            alert("error: " + response + " status: " + status + " er:" + er);
        }
    });
});