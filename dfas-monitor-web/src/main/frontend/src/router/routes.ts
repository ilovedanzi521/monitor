import Layout from "@/layout/Layout.vue";
import Login from "@/page/login/view/index.vue";
import Home from "@/page/home/view/index.vue";
import Directional from "@/page/fram/view/Directional.vue";
import MicroServiceList from "@/page/microService/view/microServiceList.vue";
import MicroServicePanel from "@/page/microService/view/microServicePanel.vue";
import MachineList from "@/page/machine/view/machineList.vue";
import MachinePanel from "@/page/machine/view/machinePanel.vue";
import WarnList from "@/page/warn/view/warnList.vue";
import ThresholdList from "@/page/warn/view/thresholdList.vue";

const routers = [
  {
    path: "/",
    name: "layout",
    component: Layout,
    children: [
      {
        path: "/directional",
        name: "directional",
        component: Directional
      },
    ]
  },
  {
    path: "/login",
    name: "login",
    component: Login
  },
];

const routerChildren = [
  {
    path: "/home",
    name: "home",
    component: Home
  },
  {
      path: "/microServiceList",
      name: "microServiceList",
      component: MicroServiceList
  },
  {
      path: "/microServicePanel",
      name: "microServicePanel",
      component: MicroServicePanel
  },
  {
      path: "/machineList",
      name: "machineList",
      component: MachineList
  },
  {
    path: "/machinePanel",
    name: "machinePanel",
    component: MachinePanel
  },
  {
    path: "/warnList",
    name: "warnList",
    component: WarnList
  },
  {
    path: "/thresholdList",
    name: "thresholdList",
    component: ThresholdList
  }
];

if ("development" === process.env.NODE_ENV) {
  routerChildren.forEach(item => {
    routers[0].children.push(item);
  });
  localStorage.setItem("Authorization", "development");
} else {
  routerChildren.forEach(item => {
    if (item.children) {
      routers.push({...item, children: item.children});
    } else {
      routers.push({...item, children: []});
    }
  });
}
export default routers;
