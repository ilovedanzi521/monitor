export default {
  data() {
    return {
      monitorList: [],
      time: ""
    };
  },
  props: {
    /**渲染数据 0：档机，1：危险，2：警告，3：正常 */
    dataList: {
      type: Array,
      default () {
        return [];
      }
    },
    width: {
      type: String
    },
    /**渲染数据类型，machine：机器，service：服务 */
    isShowTitle: {
      type: Boolean,
      default: false
    },
    type: {
      type: String,
      default: "machine", //service,machine
      //   validator: function (value) {
      //     return ["machine", "service"].indexOf(value) !== -1;
      //   }
    },
    /**是否展示提示面板 默认true,展示面板，fasle不展示面板*/
    isTool: {
      type: Boolean,
      default: true
    },
    /**那个状态需要用动画，["1","2"]代表危险的与警告的需要动画*/
    animations: {
      type: Array,
      default () {
        return ["2", "1"];
      },
      validator: function (value) {
        let isContainArray = false;
        value.forEach(item => {
          if (["1", "2", "3", "4"].indexOf(item) !== -1) {
            isContainArray = true;
          } else {
            isContainArray = false;
          }
        });
        return isContainArray;
      }
    },
    /**最多显示多少行*/
    row: {
      type: Number,
      default: 0
    },
    /**最多显示多少列*/
    col: {
      type: Number,
      default: 0
    }
  },
  computed: {
    getFormatData() {
      let monitorList = this.dataList.reduce((monitor, item) => {
        if (!monitor[item.state]) {
          monitor[item.state] = [];
          monitor[item.state].push(item);
        } else {
          monitor[item.state].push(item);
        }

        return monitor;
      }, {});

      if (this.row && this.col) {
        monitorList = this.renderRow(monitorList);
      }

      return monitorList;
    }
  },
  methods: {
    clickItem(monitor, item) {
      this.$emit("clickItem", {
        ParentItem: monitor,
        item
      });
    },
    /****渲染多少行多少列 */
    renderRow(mb) {
      let array = Object.keys(mb).map(item => {
        return mb[item];
      });
      if (this.row && this.col) {
        let computedNum = 0;
        let rows = [];
        let rememberState;
        for (let i = 0; i < array.length; i++) {
          if (computedNum < this.row) {
            rememberState = i;
            let row;
            if (array[i].length % this.col === 0) {
              row = array[i].length / this.col;
            } else {
              row = Math.floor(array[i].length / this.col) + 1;
            }
            computedNum += row;
            rows.push(row);
          }
        }
        // console.log(rows);
        // console.log(rememberState);
        let currentIndex = 0;
        let maxItemNum = 0;
        let arrayItem = [];
        let maxElememt = this.row * this.col;
        let formatArray = array
          .map((item, index) => {
            if (index <= rememberState) {
              let formatRendreItem = item
                .map(element => {
                  currentIndex++;
                  if (currentIndex <= maxElememt) {
                    // return {...element,isRendre:true}
                    return {
                      ...element
                    };
                  } else {
                    return "";
                  }
                })
                .filter(item => item.id);
              maxItemNum += rows[index] * this.col;
              arrayItem.push(rows[index] * this.col);
              return formatRendreItem;
            }

            // maxItemNum += row[index] * this.col;
            // console.log(maxItemNum);
          })
          .filter(item => Array.isArray(item));

        if (maxItemNum >= maxElememt) {
          let diff =
            maxElememt -
            (maxItemNum - arrayItem[arrayItem.length - 1]);
          formatArray[formatArray.length - 1].splice(diff);
          this.time && clearTimeout(this.time);
          if (rememberState === 0) {
            this.time = setTimeout(() => {
              this.$refs.monitor.style.justifyContent = "center";
            });
          } else {
            this.time = setTimeout(() => {
              this.$refs.monitor.style.justifyContent =
                "space-around";
            });
          }
        }

        return formatArray;
      }
    },
    getUiWidth() {
      let monitorUi = this.$refs.monitor;
      let monitorItem = monitorUi.querySelectorAll(".monitor-item-js")[0];
      monitorUi.style.width = monitorItem.offsetWidth * this.col + "px";
    }
  },
  mounted() {
    if (this.row && this.col && this.dataList.length) {
      this.getUiWidth();
      // this.monitorUi = this.$refs.monitor;
      // console.log(this.monitorUi);
      // let MaxMonitorItemHeight = (monitorUi.style.height =
      //     monitorItem.offsetHeight * this.maxRow + 20 + "px");
    }
  }
};
