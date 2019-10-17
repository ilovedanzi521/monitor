import Vue from "vue";
import Component from "vue-class-component";
import Monitor from "../../../components2/vue/Monitor.vue";
import MachineStateVO from "../vo/MachineStateVO";
import HomeMachineStateService from "../service/HomeMachineStateService";
import AxiosFun from "../../../api/AxiosFun";
import {WinResponseData} from "../../common/vo/BaseVO";

@Component({components: {Monitor}})
export default class HomeMachineStateController extends Vue {

}
