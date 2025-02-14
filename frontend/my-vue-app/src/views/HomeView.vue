<template>
  <div class="home">
    <HelloWorld />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue"; // Importing the defineComponent function from Vue to define a component
import HelloWorld from "@/components/HelloWorld.vue"; // @ is an alias to /src, importing HelloWorld component

export default defineComponent({
  name: "HomeView",
  components: {
    HelloWorld, // Registering the HelloWorld component to be used in this component's template
  },
});
</script>
