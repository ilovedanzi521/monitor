<template>
  <div class="homeContainer">
    <HomeLeft></HomeLeft>
    <HomeRight></HomeRight>
  </div>
</template>

<script lang="ts">
  import HomeController from '../controller/HomeController';
  import Component from "vue-class-component";
  import HomeLeft from "./HomeLeft.vue";
  import HomeRight from "./HomeRight.vue";

  @Component({
    components: {HomeLeft, HomeRight}
  })
  export default class Home extends HomeController {
  };
</script>

<style lang="scss" scoped>
  @import "../style/home.scss";
</style>
