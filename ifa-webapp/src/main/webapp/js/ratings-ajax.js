$(document).ready(function () {
    $.ajax({
        url: '/api/investfunds/' + $('#fund-id').text() + '/ratings',
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
                    "color": "#d23a3a",
                    "dataProvider": response,
                    "categoryField": "date",
                    "compared": false
                }],

                "panels": [{
                    recalculateToPercents: "never",

                    "title": "wartość j.u. netto w PLN",

                    "valueAxes": [{
                        "id": "axis1",
                        // "minimum": 0
                    }],

                    "stockGraphs": [{
                        "id": "g1",
                        "title": $('#fund-id').text(),
                        "lineThickness": 2,
                        "valueField": "close",
                        // "useDataSetColors": false,
                        "valueAxis": "axis1"
                    }],

                    "stockLegend": {
                        // "valueTextRegular": " ",
                        // "markerType": "none"
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
                    "fromText": "Data od:",
                    "toText": "do:",
                    "periodsText": "Zakres:",
                    "position": "bottom",
                    "dateFormat": "YYYY-MM-DD",
                    "periods": [
                        {
                            "period": "DD",
                            "count": 7,
                            "label": "7 dni"
                        }, {
                            "period": "MM",
                            "count": 1,
                            "label": "1 mies."
                        }, {
                            "period": "MM",
                            "count": 3,
                            "label": "3 mies."
                        }, {
                            "period": "MM",
                            "count": 6,
                            "label": "6 mies."
                        }, {
                            "period": "YYYY",
                            "count": 1,
                            "label": "1 rok"
                        }, {
                            "period": "YYYY",
                            "count": 3,
                            "label": "3 lata"
                        }, {
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