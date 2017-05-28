var ratings = [];
var startDate;
var endDate;
var chart;

$(document).ready(function () {
    $.when(getRatings()).done(getZigZag());
    $('#zigZag').bind('change', function () {
        getZigZagOnChange();
    });
});

function getRatings() {
    $.ajax({
        url: '/api/investfunds/' + $('#fund-id').text() + '/ratings',
        dataType: 'json',
        async: false,
        success: function (response) {
            ratings = response;
            startDate = response[1].date;
            endDate = response[response.length - 1].date;
        },
        error: function (response, status, er) {
            alert("error: " + response + " status: " + status + " er:" + er);
        }
    });
}

function getZigZag() {
    $.ajax({
        url: '/api/investfunds/' + $('#fund-id').text() + '/zigzag',
        data: {
            "value": $('#zigZag').val(),
            "startDate": startDate,
            "endDate": endDate
        },
        dataType: 'json',
        async: false,
        success: function (zigZag) {
            chart = AmCharts.makeChart("chart-container", {
                type: "stock",
                "theme": "light",

                "dataSets": [{
                    "title": $('#fund-id').text(),
                    "fieldMappings": [{
                        "fromField": "close",
                        "toField": "close"
                    }],
                    "color": "#081ba5",
                    "dataProvider": ratings,
                    "categoryField": "date",
                }, {
                    "title": $('#fund-id').text() + " (ZigZag)",
                    "fieldMappings": [{
                        "fromField": "close",
                        "toField": "close"
                    }],
                    "color": "#d23a3a",
                    "dataProvider": zigZag,
                    "categoryField": "date",
                    "compared": true
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
                        // "title": $('#fund-id').text() + " (ZigZag)",
                        "lineThickness": 1,
                        "valueField": "close",
                        "comparable": true,
                        "compareField": "close",
                        "compareGraphLineThickness": 2
                        // "useDataSetColors": false,
                        // "valueAxis": "axis1"
                    }],

                    "stockLegend": {
                        "periodValueTextComparing": "[[percents.close.close]]%",
                        "periodValueTextRegular": "[[close.close]]"
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

                // "dataSetSelector": {
                //     "position": "top"
                // },

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
}

function getZigZagOnChange() {
    $.ajax({
        url: '/api/investfunds/' + $('#fund-id').text() + '/zigzag',
        data: {
            "value": $('#zigZag').val(),
            "startDate": $('.amcharts-start-date-input').val(),
            "endDate": $('.amcharts-end-date-input').val()
        },
        dataType: 'json',
        success: function (response) {
            chart.dataSets[1].dataProvider = response;
            chart.validateData();
        },
        error: function (response, status, er) {
            alert("error: " + response + " status: " + status + " er:" + er);
        }
    });
}