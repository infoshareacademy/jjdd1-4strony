var ratings = [];
var startDate;
var endDate;
var chart;

$(document).ready(function () {
    getTimeRange();
    getRatings();
    getAjax();
    $('#zigZag').bind('change', function () {
        getAjaxOnChange();
    });
});

function getTimeRange() {
    $.ajax({
        url: '/resources/investfund/timeRange/json/' + $('#fund-id').text(),
        dataType: 'json',
        success: function (response) {
            startDate = response.start;
            endDate = response.end;
        },
        error: function (response, status, er) {
            alert("error: " + response + " status: " + status + " er:" + er);
        }
    });
}

function getRatings() {
    $.ajax({
        url: '/resources/ratings/all/json/' + $('#fund-id').text(),
        dataType: 'json',
        success: function (response) {
            ratings = response;
        },
        error: function (response, status, er) {
            alert("error: " + response + " status: " + status + " er:" + er);
        }
    });
}

function getAjax() {
    $.ajax({
        url: '/resources/zigzag/all/json/' + $('#fund-id').text(),
        data: {
            "zigZag": $('#zigZag').val(),
            "startDate": startDate,
            "endDate": endDate
        },
        dataType: 'json',
        success: function (response) {
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
                    "dataProvider": response,
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
                            // "selected": true,
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

function getAjaxOnChange() {
    $.ajax({
        url: '/resources/zigzag/all/json/' + $('#fund-id').text(),
        data: {
            "zigZag": $('#zigZag').val(),
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