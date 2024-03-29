import { WinFdialog } from "./fDialog";

import { WinDicSelect } from "./win-dic-select";

import { WinSelect } from "./win-select";

import { WinTooltip } from "./win-tooltip";

import MHIUtil from "../mixin/MHIUtil";
import {
  win_message_box_success,
  win_message_box_error,
  win_message_box_info,
  win_message_box_warning
} from "./win-message-box";

const pluginsPage = {
  install(Vue, option) {
    /**注册组件*/
    Vue.component("WinFdialog", WinFdialog);
    Vue.component("WinDicSelect", WinDicSelect);
    Vue.component("WinSelect", WinSelect);
    Vue.component("WinTooltip", WinTooltip);

    /**注册指令*/
    /* from光标定位到那个input框 */
    Vue.directive("noDataTip", {
      bind(el) {
        el.querySelector(".vxe-table--empty-text").style.position = "absolute";
        el.querySelector(".vxe-table--empty-text").style.left = "0";
        el.querySelector(".vxe-table--empty-text").style.textAlign = "right";
      }
    });

    Vue.directive("focus", {
      inserted(el) {
        let input = el.querySelector(".el-input__inner");
        input.focus();
      }
    }),
      Vue.directive("winfocus", {
        inserted(el) {
          el.focus();
        }
      });

    /*测试input唯一标识**/
    Vue.directive("testName", {
      inserted(el, binding) {
        // if (process.env.NODE_ENV != developmen1111t) {
        //     return
        // }//代码判断生产环境下不执行任何代码
        let e = el.parentNode.parentNode;
        let elNode = getChildNodes(e);
        let type = 1;
        elNode.forEach((item, index) => {
          if (item.localName == "input") {
            let placeholder = item.getAttribute("placeholder");
            if (!placeholder) {
              item.setAttribute(
                "test_name",
                `${binding.value.TEST_NAME}-${index}`
              );
            } else {
              item.setAttribute(
                "test_name",
                `${binding.value.TEST_NAME}-${placeholder}`
              );
            }
          }
          if (item.localName == "textarea") {
            item.setAttribute(
              "test_name",
              `${binding.value.TEST_NAME}-${index}`
            );
          }

          if (item.localName == "button") {
            if (type == 1) {
              item.setAttribute("test_name", `${binding.value.TEST_NAME}-确定`);
              type = 2;
            } else {
              item.setAttribute(
                "test_name",
                `${binding.value.TEST_NAME}-取消 `
              );
              type = 1;
            }
          }
        });
      }
    });

    /**将方法注册到Vue原型链上 */
    /**提示信息,success */
    Vue.prototype.win_message_success = function(info) {
      this.$message({
        showClose: true,
        type: "success",
        message: info,
        customClass: "win-success",
        iconClass: "win win-zijintiaozhengfuhe",
        closeOnClickModal: false
      });
    };

    /**提示信息,errorMessage */
    Vue.prototype.win_message_error = function(info) {
      this.$message({
        showClose: true,
        type: "error",
        message: info,
        customClass: "win-error",
        iconClass: "win win-zijintiaozhengfuhe",
        closeOnClickModal: false
      });
    };

    /**提示信息,info */
    Vue.prototype.win_message_info = function(info) {
      this.$message({
        showClose: true,
        type: "info",
        message: info,
        closeOnClickModal: false
      });
    };

    /**提示信息,info */
    Vue.prototype.win_message_warn = function(info) {
      this.$message({
        showClose: true,
        type: "warning",
        message: info
      });
    };

    Vue.prototype.win_message_box_success = win_message_box_success;
    Vue.prototype.win_message_box_error = win_message_box_error;
    Vue.prototype.win_message_box_info = win_message_box_info;
    Vue.prototype.win_message_box_warning = win_message_box_warning;

    /**挂MHIUti的所有方法到vue prototype */
    Object.keys(MHIUtil).forEach(item => {
      Vue.prototype[item] = MHIUtil;
    });
  }
};

function getChildNodes(node) {
  var nodes = [...node.childNodes];
  // console.log(node.childNodes)
  var arr = [];
  for (var i = 0; i < nodes.length; i++) {
    // console.log(nodes[i])
    var childNode = nodes[i];
    if (childNode.nodeType == 1) {
      arr.push(childNode);
      var temp = getChildNodes(childNode); //递归调用getChildNodes方法，查看当前子节点下是否还有子节点
      arr = arr.concat(temp); //将递归获取的子节点数组与之前的数组拼接成一个数组
    }
  }
  return arr;
}

export default pluginsPage;
