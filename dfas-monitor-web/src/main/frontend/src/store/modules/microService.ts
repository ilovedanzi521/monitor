/*
 * @Descripttion:
 * @Author: wangyaoheng
 * @Date: 2019-07-12 17:08:40
 * @LastEditors: wangyaoheng
 * @LastEditTime: 2019-07-13 10:26:24
 */
const state = {
    microServiceInfo: {
        microServiceAlias: "",
        microServiceName: ""
    },
    // 展示更多交易对手信息控制
    showMore: {
        flag: false,
        offsetTop: 0
    }
};

const mutations = {
    // tslint:disable-next-line: no-shadowed-variable
    setShowMore(state, payload) {
        state.showMore = {
            flag: false,
            offsetTop: 0
        };
        if (payload.hasOwnProperty("flag")) {
            state.showMore.flag = payload.flag;
        }
        if (payload.hasOwnProperty("offsetTop")) {
            state.showMore.offsetTop = payload.offsetTop;
        }
    },
    // tslint:disable-next-line: no-shadowed-variable
    setMicroServiceInfo(state, payload) {
        state.microServiceInfo = {
            microServiceAlias: "",
            microServiceName: ""
        };
        if (payload.hasOwnProperty("microServiceAlias")) {
            state.microServiceInfo.microServiceAlias = payload.microServiceAlias;
        }
        if (payload.hasOwnProperty("rivalName")) {
            state.microServiceInfo.microServiceName = payload.microServiceName;
        }
    }
};

const actions = {};
export default {
    state,
    mutations,
    actions
};
