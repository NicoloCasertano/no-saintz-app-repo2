import "./chunk-WDMUDEB6.js";

// node_modules/wavesurfer.js/dist/plugins/zoom.esm.js
var t = class {
  constructor() {
    this.listeners = {};
  }
  on(t2, e2, s2) {
    if (this.listeners[t2] || (this.listeners[t2] = /* @__PURE__ */ new Set()), this.listeners[t2].add(e2), null == s2 ? void 0 : s2.once) {
      const s3 = () => {
        this.un(t2, s3), this.un(t2, e2);
      };
      return this.on(t2, s3), s3;
    }
    return () => this.un(t2, e2);
  }
  un(t2, e2) {
    var s2;
    null === (s2 = this.listeners[t2]) || void 0 === s2 || s2.delete(e2);
  }
  once(t2, e2) {
    return this.on(t2, e2, { once: true });
  }
  unAll() {
    this.listeners = {};
  }
  emit(t2, ...e2) {
    this.listeners[t2] && this.listeners[t2].forEach((t3) => t3(...e2));
  }
};
var e = class extends t {
  constructor(t2) {
    super(), this.subscriptions = [], this.options = t2;
  }
  onInit() {
  }
  _init(t2) {
    this.wavesurfer = t2, this.onInit();
  }
  destroy() {
    this.emit("destroy"), this.subscriptions.forEach((t2) => t2());
  }
};
var s = { scale: 0.5, deltaThreshold: 5, exponentialZooming: false, iterations: 20 };
var i = class _i extends e {
  constructor(t2) {
    super(t2 || {}), this.wrapper = void 0, this.container = null, this.accumulatedDelta = 0, this.pointerTime = 0, this.oldX = 0, this.endZoom = 0, this.startZoom = 0, this.onWheel = (t3) => {
      if (this.wavesurfer && this.container && !(Math.abs(t3.deltaX) >= Math.abs(t3.deltaY)) && (t3.preventDefault(), this.accumulatedDelta += -t3.deltaY, 0 === this.startZoom && this.options.exponentialZooming && (this.startZoom = this.wavesurfer.getWrapper().clientWidth / this.wavesurfer.getDuration()), 0 === this.options.deltaThreshold || Math.abs(this.accumulatedDelta) >= this.options.deltaThreshold)) {
        const e2 = this.wavesurfer.getDuration(), s2 = 0 === this.wavesurfer.options.minPxPerSec ? this.wavesurfer.getWrapper().scrollWidth / e2 : this.wavesurfer.options.minPxPerSec, i2 = t3.clientX - this.container.getBoundingClientRect().left, o = this.container.clientWidth, n = this.wavesurfer.getScroll();
        i2 === this.oldX && 0 !== this.oldX || (this.pointerTime = (n + i2) / s2), this.oldX = i2;
        const r = this.calculateNewZoom(s2, this.accumulatedDelta), h = o / r * (i2 / o);
        r * e2 < o ? (this.wavesurfer.zoom(o / e2), this.container.scrollLeft = 0) : (this.wavesurfer.zoom(r), this.container.scrollLeft = (this.pointerTime - h) * r), this.accumulatedDelta = 0;
      }
    }, this.calculateNewZoom = (t3, e2) => {
      let s2;
      if (this.options.exponentialZooming) {
        const i2 = e2 > 0 ? Math.pow(this.endZoom / this.startZoom, 1 / (this.options.iterations - 1)) : Math.pow(this.startZoom / this.endZoom, 1 / (this.options.iterations - 1));
        s2 = Math.max(0, t3 * i2);
      } else s2 = Math.max(0, t3 + e2 * this.options.scale);
      return Math.min(s2, this.options.maxZoom);
    }, this.options = Object.assign({}, s, t2);
  }
  static create(t2) {
    return new _i(t2);
  }
  onInit() {
    var t2;
    this.wrapper = null === (t2 = this.wavesurfer) || void 0 === t2 ? void 0 : t2.getWrapper(), this.wrapper && (this.container = this.wrapper.parentElement, this.container.addEventListener("wheel", this.onWheel), void 0 === this.options.maxZoom && (this.options.maxZoom = this.container.clientWidth), this.endZoom = this.options.maxZoom);
  }
  destroy() {
    this.wrapper && this.wrapper.removeEventListener("wheel", this.onWheel), super.destroy();
  }
};
export {
  i as default
};
//# sourceMappingURL=wavesurfer__js_dist_plugins_zoom__js.js.map
