<template>
  <div class="forest-grid">
    <div v-for="(row, rowIndex) in state.grid" :key="rowIndex" class="row">
      <div
        v-for="(cell, cellIndex) in row"
        :key="cellIndex"
        :class="getCellClass(cell)"
        class="cell"
      ></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import { useStore } from "vuex";
import { state } from "../socket";

// Function to apply different styles based on cell value
const getCellClass = (cell: number) => {
  switch (cell) {
    case 0:
      return "empty"; // Empty cell
    case 1:
      return "tree"; // Tree cell
    case 2:
      return "fire"; // Fire cell
    case 3:
      return "ashes"; // Ashes cell
    default:
      return "";
  }
};
</script>

<style scoped>
/* Example styles for different cell types */

.forest-grid {
  display: flex;
  grid-template-columns: repeat(auto-fill, minmax(50px, 1fr));
  grid-gap: 2px;
  width: 100%;
  justify-content: center;
}

.cell {
  width: 10px; /* Fixed width of each cell */
  height: 10px; /* Fixed height of each cell */
  /* margin: 1px; Optional: if you want some space between cells */
  border: 0.0001px solid #ccc; /* Border around each cell */
}

.empty {
  background-color: white;
}

.tree {
  background-color: green;
}

.fire {
  background-color: red;
}

.ashes {
  background-color: gray;
}
</style>
