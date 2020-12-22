<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>ArcGIS API for JavaScript Tutorials: Get map coordinates</title>
    <style>
        html, body, #viewDiv {
            padding: 0;
            margin: 0;
            height: 100%;
            width: 100%;
        }
    </style>

    <link rel="stylesheet" href="https://js.arcgis.com/4.18/esri/themes/light/main.css">
    <script src="https://js.arcgis.com/4.18/"></script>

    <script>
        require([
            "esri/Map",
            "esri/views/MapView",
            "esri/widgets/CoordinateConversion"
        ], function(Map, MapView, CoordinateConversion) {

            var map = new Map({
                basemap: "topo-vector"
            });

            var view = new MapView({
                container: "viewDiv",
                map: map,
                center: [-118.80543,34.02700],
                zoom: 13
            });


            function showCoordinates(pt) {
                var coords = pt.latitude + "," + pt.longitude
                console.log(coords)
            }

            //在鼠标移动的时候，输出坐标
            view.on(["pointer-down","pointer-move"], function(evt) {
                showCoordinates(view.toMap({ x: evt.x, y: evt.y }));
            });

            var coordinateConversionWidget = new CoordinateConversion({
                view: view
            });

            view.ui.add(coordinateConversionWidget, "bottom-right");

        });
    </script>
</head>
<body>
<div id="viewDiv"></div>
</body>
</html>