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
            "esri/Map",     //加载特定于创建地图的代码
            "esri/views/MapView",   //加载允许以2D模式查看地图的代码
            "esri/widgets/CoordinateConversion",
            "esri/Graphic"
        ], function(Map, MapView, CoordinateConversion,Graphic) {

            var map = new Map({
                basemap: "topo-vector"
            });

            var view = new MapView({
                center: [102.54, 30.05],
                container: "viewDiv",
                map: map,
                zoom: 9
            });

            //加入坐标转换的功能
            var coordinateConversionWidget = new CoordinateConversion({
                view: view
            });
            view.ui.add(coordinateConversionWidget, "bottom-right");

            //加入画图的代码
            // First create a point geometry (this is the location of the Titanic)
            var point = {
                    type: "point", // autocasts as new Point()
                    longitude: 102.54,
                    latitude: 30.05
                };
            // Create a symbol for drawing the point
            var markerSymbol = {
                type: "simple-marker", // autocasts as new SimpleMarkerSymbol()
                color: [226, 119, 40],
                outline: {
                    // autocasts as new SimpleLineSymbol()
                    color: [255, 255, 255],
                    width: 2
                }
            };
            // Create a graphic and add the geometry and symbol to it
            var pointGraphic = new Graphic({
                geometry: point,
                symbol: markerSymbol
            });
            // Add the graphics to the view's graphics layer
            // view.graphics.addMany([pointGraphic]);
            view.graphics.add(pointGraphic);


            //在鼠标点击的时候，输出坐标     ["pointer-down","pointer-move"]
            view.on(["click"], function(evt) {
                latitude_value = evt.mapPoint.latitude
                longitude_value = evt.mapPoint.longitude
                console.log(latitude_value+","+longitude_value)
            });


        });
    </script>
</head>
<body>
<div id="viewDiv"></div>
</body>
</html>