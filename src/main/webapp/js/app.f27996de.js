(function(t) {
    function e(e) {
        for (var n, o, r = e[0], h = e[1], c = e[2], u = 0, d = []; u < r.length; u++) o = r[u],
        Object.prototype.hasOwnProperty.call(a, o) && a[o] && d.push(a[o][0]),
            a[o] = 0;
        for (n in h) Object.prototype.hasOwnProperty.call(h, n) && (t[n] = h[n]);
        l && l(e);
        while (d.length) d.shift()();
        return s.push.apply(s, c || []),
            i()
    }
    function i() {
        for (var t, e = 0; e < s.length; e++) {
            for (var i = s[e], n = !0, r = 1; r < i.length; r++) {
                var h = i[r];
                0 !== a[h] && (n = !1)
            }
            n && (s.splice(e--, 1), t = o(o.s = i[0]))
        }
        return t
    }
    var n = {},
        a = {
            app: 0
        },
        s = [];
    function o(e) {
        if (n[e]) return n[e].exports;
        var i = n[e] = {
            i: e,
            l: !1,
            exports: {}
        };
        return t[e].call(i.exports, i, i.exports, o),
            i.l = !0,
            i.exports
    }
    o.m = t,
        o.c = n,
        o.d = function(t, e, i) {
            o.o(t, e) || Object.defineProperty(t, e, {
                enumerable: !0,
                get: i
            })
        },
        o.r = function(t) {
            "undefined" !== typeof Symbol && Symbol.toStringTag && Object.defineProperty(t, Symbol.toStringTag, {
                value: "Module"
            }),
                Object.defineProperty(t, "__esModule", {
                    value: !0
                })
        },
        o.t = function(t, e) {
            if (1 & e && (t = o(t)), 8 & e) return t;
            if (4 & e && "object" === typeof t && t && t.__esModule) return t;
            var i = Object.create(null);
            if (o.r(i), Object.defineProperty(i, "default", {
                enumerable: !0,
                value: t
            }), 2 & e && "string" != typeof t) for (var n in t) o.d(i, n,
                function(e) {
                    return t[e]
                }.bind(null, n));
            return i
        },
        o.n = function(t) {
            var e = t && t.__esModule ?
                function() {
                    return t["default"]
                }: function() {
                    return t
                };
            return o.d(e, "a", e),
                e
        },
        o.o = function(t, e) {
            return Object.prototype.hasOwnProperty.call(t, e)
        },
        o.p = "";
    var r = window["webpackJsonp"] = window["webpackJsonp"] || [],
        h = r.push.bind(r);
    r.push = e,
        r = r.slice();
    for (var c = 0; c < r.length; c++) e(r[c]);
    var l = h;
    s.push([0, "chunk-vendors"]),
        i()
})({
    0 : function(t, e, i) {
        t.exports = i("56d7")
    },
    "034f": function(t, e, i) {
        "use strict";
        var n = i("85ec"),
            a = i.n(n);
        a.a
    },
    "137f": function(t, e, i) {},
    "56d7": function(t, e, i) {
        "use strict";
        i.r(e);
        i("e260"),
            i("e6cf"),
            i("cca6"),
            i("a79d");
        var n, a, s = i("2b0e"),
            o = function() {
                var t = this,
                    e = t.$createElement,
                    i = t._self._c || e;
                return i("div", {
                        attrs: {
                            id: "app"
                        }
                    },
                    [i("router-view")], 1)
            },
            r = [],
            h = (i("034f"), i("2877")),
            c = {},
            l = Object(h["a"])(c, o, r, !1, null, null, null),
            u = l.exports,
            d = i("8c4f"),
            f = function() {
                var t = this,
                    e = t.$createElement,
                    i = t._self._c || e;
                return i("div", {
                        staticClass: "home"
                    },
                    [i("el-container", [i("el-aside", {
                            attrs: {
                                width: "200px"
                            }
                        },
                        [i("div", [t._v("agent选择：")]), i("humanSelector"), i("el-row", [i("el-button", {
                                attrs: {
                                    type: "primary",
                                    plain: ""
                                },
                                on: {
                                    click: t.importJson
                                }
                            },
                            [t._v("批量导入")])], 1)], 1), i("el-container", [i("el-header", {
                            staticClass: "home-header"
                        },
                        [t._v("agent展示界面")]), i("el-main", {
                            staticClass: "home-main"
                        },
                        [i("Map")], 1), i("el-footer", {
                            staticClass: "home-footer"
                        },
                        [i("Information")], 1)], 1)], 1)], 1)
            },
            v = [],
            g = (i("4160"), i("159b"), i("bc3a")),
            m = i.n(g),
            p = new s["default"],
            w = function() {
                var t = this,
                    e = t.$createElement,
                    i = t._self._c || e;
                return i("div", [i("canvas", {
                    attrs: {
                        id: "hex-canvas",
                        width: "2200",
                        height: "2200"
                    },
                    on: {
                        click: t.click
                    }
                })])
            },
            b = [],
            y = {
                soldier: "./img/soldier.svg",
                chariots: "./img/chariots.svg",
                tank: "./img/tank.svg",
                commander: "./img/commander.svg"
            },
            x = {
                mountain: "./img/mountain.svg",
                plain: "./img/plain.svg",
                river: "./img/river.svg",
                city: "./img/city.svg",
                forests: "./img/forests.svg"
            },
            _ = {
                agentUrl: y,
                areaUrl: x
            },
            O = _,
            k = Object(h["a"])(O, n, a, !1, null, null, null),
            T = (k.exports, i("cb29"), i("d4ec")),
            j = i("bee2"),
            $ = function() {
                function t(e, i, n) {
                    Object(T["a"])(this, t),
                        this.radius = i,
                        this.height = Math.sqrt(3) * i,
                        this.width = 2 * i,
                        this.side = 1.5 * i,
                        this.canvas = document.getElementById(e),
                        this.context = this.canvas.getContext("2d"),
                        this.canvasOriginX = 0,
                        this.canvasOriginY = 0,
                        this.canvas.addEventListener("mousedown", this.clickEvent.bind(this), !1),
                        this.showRowNCol = n
                }
                return Object(j["a"])(t, [{
                    key: "addIcon",
                    value: function(t, e, i) {
                        var n = this,
                            a = this.getCenter(t, e),
                            s = new Image;
                        s.src = i,
                            s.onload = function() {
                                n.context.drawImage(s, a.x - 8, a.y - 8)
                            }
                    }
                },
                    {
                        key: "getCenter",
                        value: function(t, e) {
                            var i = e % 2 == 0 ? t * this.height + this.height / 2 + this.canvasOriginY: t * this.height + this.canvasOriginY + this.height / 2 + this.height / 2,
                                n = e * this.side + this.canvasOriginX + this.width / 2;
                            return {
                                x: n,
                                y: i
                            }
                        }
                    },
                    {
                        key: "con2Area",
                        value: function(t, e, i, n) {
                            var a = this.getCenter(t, e),
                                s = this.getCenter(i, n);
                            this.context.beginPath(),
                                this.context.moveTo(a.x, a.y),
                                this.context.lineTo(s.x, s.y),
                                this.context.stroke()
                        }
                    },
                    {
                        key: "drawHexAtColRow",
                        value: function(t, e, i) {
                            var n = t % 2 == 0 ? e * this.height + this.canvasOriginY: e * this.height + this.canvasOriginY + this.height / 2,
                                a = t * this.side + this.canvasOriginX;
                            this.drawHex(a, n, i, "")
                        }
                    },
                    {
                        key: "drawHex",
                        value: function(t, e, i, n) {
                            this.context.strokeStyle = "#000",
                                this.context.beginPath(),
                                this.context.moveTo(t + this.width - this.side, e),
                                this.context.lineTo(t + this.side, e),
                                this.context.lineTo(t + this.width, e + this.height / 2),
                                this.context.lineTo(t + this.side, e + this.height),
                                this.context.lineTo(t + this.width - this.side, e + this.height),
                                this.context.lineTo(t, e + this.height / 2),
                            i && (this.context.fillStyle = i, this.context.fill()),
                                this.context.closePath(),
                                this.context.stroke(),
                            n && (this.context.font = "8px", this.context.fillStyle = "#000", this.context.fillText(n, t + this.width / 2 - this.width / 4, e + (this.height - 5)))
                        }
                    },
                    {
                        key: "getRelativeCanvasOffset",
                        value: function() {
                            var t = 0,
                                e = 0,
                                i = this.canvas;
                            if (i.offsetParent) {
                                do {
                                    t += i.offsetLeft, e += i.offsetTop
                                } while ( i = i . offsetParent );
                                return {
                                    x: t,
                                    y: e
                                }
                            }
                        }
                    },
                    {
                        key: "getSelectedTile",
                        value: function(t, e) {
                            var i = this.getRelativeCanvasOffset();
                            t -= i.x,
                                e -= i.y;
                            var n = Math.floor(t / this.side),
                                a = Math.floor(n % 2 == 0 ? Math.floor(e / this.height) : Math.floor((e + .5 * this.height) / this.height) - 1);
                            if (t > n * this.side && t < n * this.side + this.width - this.side) {
                                var s = new Object;
                                s.x = n * this.side,
                                    s.y = n % 2 == 0 ? a * this.height: a * this.height + this.height / 2;
                                var o = new Object;
                                o.x = s.x,
                                    o.y = s.y + this.height / 2;
                                var r = new Object;
                                r.x = s.x + this.width - this.side,
                                    r.y = s.y;
                                var h = new Object;
                                h.x = t,
                                    h.y = e,
                                this.isPointInTriangle(h, s, o, r) && (n--, n % 2 != 0 && a--);
                                var c = new Object;
                                c = o;
                                var l = new Object;
                                l.x = c.x,
                                    l.y = c.y + this.height / 2;
                                var u = new Object;
                                u.x = l.x + (this.width - this.side),
                                    u.y = l.y,
                                this.isPointInTriangle(h, c, l, u) && (n--, n % 2 == 0 && a++)
                            }
                            return {
                                row: a,
                                column: n
                            }
                        }
                    },
                    {
                        key: "sign",
                        value: function(t, e, i) {
                            return (t.x - i.x) * (e.y - i.y) - (e.x - i.x) * (t.y - i.y)
                        }
                    },
                    {
                        key: "isPointInTriangle",
                        value: function(t, e, i, n) {
                            var a, s, o;
                            return a = this.sign(t, e, i) < 0,
                                s = this.sign(t, i, n) < 0,
                                o = this.sign(t, n, e) < 0,
                            a == s && s == o
                        }
                    },
                    {
                        key: "clickEvent",
                        value: function(t) {
                            var e = t.pageX,
                                i = t.pageY,
                                n = e - this.canvasOriginX,
                                a = i - this.canvasOriginY,
                                s = this.getSelectedTile(n, a);
                            if (s.column >= 0 && s.row >= 0 && s.column < this.cols && s.row < this.rows) {
                                var o = s.column % 2 == 0 ? s.row * this.height + this.canvasOriginY + 6 : s.row * this.height + this.canvasOriginY + 6 + this.height / 2,
                                    r = s.column * this.side + this.canvasOriginX;
                                this.drawHex(r, o - 6, "rgba(110,0,0,0.3)", ""),

                                    console.log("row:" + s.row + " column:" + s.column),
                                    this.showRowNCol(s)
                                //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
                                var curWwwPath = window.document.location.href;
                                //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
                                var pathName = window.document.location.pathname;
                                var pos = curWwwPath.indexOf(pathName);
                                //获取主机地址，如： http://localhost:8083
                                var localhostPaht = curWwwPath.substring(0, pos);
                                //获取带"/"的项目名，如：/uimcardprj
                                var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
                                    console.log("path:"+localhostPaht+projectName)
                                /*http://localhost:8080/uestc*/
                            }
                        }
                    },
                    {
                        key: "drawHexGrid",
                        value: function(t, e, i, n, a) {
                            var s, o;
                            this.rows = t,
                                this.cols = e,
                                this.canvasOriginX = i,
                                this.canvasOriginY = n;
                            for (var r = "",
                                     h = !1,
                                     c = 0; c < e; c++) {
                                for (var l = 0; l < t; l++) h ? (s = c * this.side + i, o = l * this.height + n + .5 * this.height) : (s = c * this.side + i, o = l * this.height + n),
                                a && (r = l + "," + c),
                                    this.drawHex(s, o, "#ddd", r);
                                h = !h
                            }
                        }
                    }]),
                    t
            } ();

        var S = {
                name: "Map",
                data: function() {
                    return {
                        x: String,
                        y: String
                    }
                },
                mounted: function() {
                    var t = this,
                        e = new $("hex-canvas", 25, (function(e) {
                            t.x = e.row,
                                t.y = e.column,
                                p.$emit("showClick", e)
                        }));
                    e.drawHexGrid(50, 50, 0, 0, !0),
                        p.$on("drawAgent", (function(t) {
                            var i = "";
                            JSON.pares('{"soldier":"./img/soldier.svg","chariots":"./img/chariots.svg", "tank":"./img/tank.svg","commander":"./img/commander.svg"}', (function(e, n) {
                                e == t.type && (i = n)
                            })),
                                e.addIcon(t.row, t.column, i)
                        })),
                        p.$on("drawArea", (function(t) {
                            var i = "";
                            JSON.parse('{"mountain":"./img/mountain.svg","plain":"./img/plain.svg", "river":"./img/river.svg","city":"./img/city.svg","forests":"./img/forests.svg"}', (function(e, n) {
                                e == t.type && (i = n)
                            })),
                                e.addIcon(t.row, t.column, i)
                        }))
                },
                methods: {
                    click: function() {}
                }
            },
            C = S,
            I = Object(h["a"])(C, w, b, !1, null, "3affb444", null),
            M = I.exports,
            P = function() {
                var t = this,
                    e = t.$createElement,
                    i = t._self._c || e;
                return i("div", [i("div", {
                        attrs: {
                            id: "info_h2"
                        }
                    },
                    [t._v("基本信息：")]), i("div", {
                        attrs: {
                            id: "info_h3"
                        }
                    },
                    [t._v("兵种：" + t._s(t.agentType))]), i("div", {
                        attrs: {
                            id: "info_h3"
                        }
                    },
                    [t._v("地形：" + t._s(t.areaType))]), i("div", {
                        attrs: {
                            id: "info_h3"
                        }
                    },
                    [t._v("精度：" + t._s(t.latitude) + ";维度：" + t._s(t.longitude))]), i("div", {
                        attrs: {
                            id: "info_h2"
                        }
                    },
                    [t._v("区域信息：")]), i("div", {
                        attrs: {
                            id: "info_h3"
                        }
                    },
                    [t._v("周围50圆域自己人：" + t._s(t.dist_50))]), i("div", {
                        attrs: {
                            id: "info_h3"
                        }
                    },
                    [t._v("周围100圆域自己人：" + t._s(t.dist_100))]), i("div", {
                        attrs: {
                            id: "info_h3"
                        }
                    },
                    [t._v("周围坦克数量：" + t._s(t.num_tank))]), i("div", {
                        attrs: {
                            id: "info_h3"
                        }
                    },
                    [t._v("周围指挥官数量：" + t._s(t.num_commander))]), i("div"), i("div", {
                        attrs: {
                            id: "info_h3"
                        }
                    },
                    [t._v("标签：" + t._s(t.label))])])
            },
            N = [],
            A = (i("a9e3"), {
                name: "Information",
                data: function() {
                    return {
                        agentType: Number,
                        areaType: Number,
                        latitude: Number,
                        longitude: Number,
                        dist_50: Number,
                        dist_100: Number,
                        num_tank: Number,
                        num_commander: Number,
                        label: Number,
                        tile: {}
                    }
                },
                mounted: function() {

                    var t = this;
                    p.$on("showClick", (function(e) {
                        t.loadTest(e)
                    }))
                },
                created: function() {},
                methods: {
                    loadTest: function(t) {
                        var e = this;
                        this.tile = t;
                        var i = "/uestc/areaInfo?x=" + this.tile.row + "&y=" + this.tile.column;
                        this.$post(i, "", "get").then((function(t) {
                            console.log(t)
                            e.agentType = t.type,
                                e.areaType = t.topography,
                                e.latitude = t.latitude,
                                e.longitude = t.longitude,
                                e.dist_50 = t.dist_50,
                                e.dist_100 = t.dist_100,
                                e.num_tank = t.num_tank,
                                e.num_commander = t.num_commander,
                                e.label = t.label
                        })).
                        catch((function(t) {
                            console.log(t)
                        }))
                    }
                }
            }),
            E = A,
            H = (i("df06"), Object(h["a"])(E, P, N, !1, null, "94c0183c", null)),
            Y = H.exports,
            X = function() {
                var t = this,
                    e = t.$createElement,
                    i = t._self._c || e;
                return i("div", [i("el-select", {
                        attrs: {
                            placeholder: "请选择"
                        },
                        on: {
                            change: t.select
                        },
                        model: {
                            value: t.label,
                            callback: function(e) {
                                t.label = e
                            },
                            expression: "label"
                        }
                    },
                    t._l(t.options, (function(t) {
                        return i("el-option", {
                            key: t.label,
                            attrs: {
                                label: t.label,
                                value: t.value
                            }
                        })
                    })), 1), i("el-button", {
                        attrs: {
                            type: "text"
                        },
                        on: {
                            click: t.open
                        }
                    },
                    [t._v("确认")])], 1)
            },
            J = [],
            R = (i("b64b"), {
                name: "humanSelector",
                components: {},
                data: function() {
                    return {
                        tile: {},
                        options: [{
                            value: "1",
                            label: "单兵"
                        },
                            {
                                value: "2",
                                label: "战车"
                            },
                            {
                                value: "3",
                                label: "坦克"
                            },
                            {
                                value: "4",
                                label: "指挥官"
                            }],
                        label: ""
                    }
                },
                mounted: function() {
                    var t = this;
                    p.$on("showClick", (function(e) {
                        t.tile = e
                    }))
                },
                methods: {
                    select: function(t) {
                        this.label = t
                    },
                    open: function() {
                        var t = this;
                        0 == Object.keys(this.tile).length || "" == this.label ? this.$alert("请选择对应的区域或者agent") : this.$alert("确定在" + this.tile.row + "行" + this.tile.column + "列添加agent吗？", "添加吗", {
                            confirmButtonText: "确定",
                            callback: function(e) {
                                t.addAgentInMap(),
                                    t.$message({
                                        type: "info",
                                        message: "action: ".concat(e)
                                    })
                            }
                        })
                    },
                    addAgentInMap: function() {
                        var t = "/addAgent/x=" + this.tile.row + "&y=" + this.tile.column + "type=" + this.label;
                        this.$post(t, "", "post").then((function(t) {
                            p.$emit("drawAgent", t.data.data)
                        })).
                        catch((function(t) {
                            console.log(t)
                        }))
                    }
                }
            }),
            L = R,
            U = Object(h["a"])(L, X, J, !1, null, null, null),
            B = U.exports,
            G = {
                name: "Home",
                components: {
                    Map: M,
                    Information: Y,
                    humanSelector: B
                },
                data: function() {
                    return {}
                },
                computed: {
                    windowWidth: function() {
                        return window.screen.width - 200
                    },
                    windowHeight: function() {
                        return window.screen.height - 120
                    }
                },
                mounted: {
                    drawMapArea: function() {
                        var t = "/mapAreaInfo";
                        this.$post(t, "", "get").then((function(t) {
                            t.data.data.forEach((function(t) {
                                p.$emit("drawArea", elment)
                            }))
                        }))
                    }
                },
                methods: {
                    importJson: function() {
                        m.a.get("./demo.json", {}).then((function(t) {
                            t.data.data.forEach((function(t) {
                                p.$emit("drawAgent", t)
                            }))
                        }))
                    }
                }
            },
            q = G,
            W = (i("dd81"), i("b5f2"), Object(h["a"])(q, f, v, !1, null, "999f1778", null)),
            z = W.exports;
        s["default"].use(d["a"]);
        var D = [{
                path: "/",
                name: "Home",
                component: z
            }],
            F = new d["a"]({
                mode: "hash",
                base: "",
                routes: D
            }),
            K = F,
            Q = i("2f62");
        s["default"].use(Q["a"]);
        var V = new Q["a"].Store({
                state: {},
                mutations: {},
                actions: {},
                modules: {}
            }),
            Z = i("5c96"),
            tt = i.n(Z),
            et = (i("0fae"), i("4328")),
            it = i.n(et);
        function nt(t) {
            return it.a.stringify(t)
        }
        function at(t) {
            var e = m.a.create({
                baseURL: "",
                timeout: 5e3
            });
            return e.interceptors.response.use((function(t) {
                return t.data
            }), (function(t) {
                console.log(t)
            })),
                e(t)
        }
        var st = function(t, e, i) {
            return at({
                url: t,
                method: i,
                data: nt(e)
            })
        };
        s["default"].config.productionTip = !1,
            s["default"].use(tt.a),
            s["default"].prototype.$post = st,
            new s["default"]({
                router: K,
                store: V,
                render: function(t) {
                    return t(u)
                }
            }).$mount("#app")
    },
    "711f": function(t, e, i) {},
    "85ec": function(t, e, i) {},
    b5f2: function(t, e, i) {
        "use strict";
        var n = i("711f"),
            a = i.n(n);
        a.a
    },
    dd81: function(t, e, i) {
        "use strict";
        var n = i("e18c"),
            a = i.n(n);
        a.a
    },
    df06: function(t, e, i) {
        "use strict";
        var n = i("137f"),
            a = i.n(n);
        a.a
    },
    e18c: function(t, e, i) {}
});
//# sourceMappingURL=app.f27996de.js.map
