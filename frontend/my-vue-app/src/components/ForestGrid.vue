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
import { state } from "../socket/socket";

// Function to determine the CSS class for a cell
const getCellClass = (cell: number) => {
  switch (cell) {
    case 0:
      return "empty"; // Represents an empty cell
    case 1:
      return "tree"; // Represents a tree
    case 2:
      return "fire"; // Represents fire
    case 3:
      return "ashes"; // Represents ashes after burning
    default:
      return "";
  }
};
</script>

<style scoped>
/* Grid container */
.forest-grid {
  display: flex;
  grid-template-columns: repeat(auto-fill, minmax(50px, 1fr));
  width: 100%;
  justify-content: center;
}

/* Cell styling */
.cell {
  width: 10px;
  height: 10px;
  border: 0.0001px solid #ccc;
}

/* Different cell types */
.empty {
  background-color: burlywood;
}

.tree {
  background: url("@/assets/cell-styles/tree.png");
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center;
}

.fire {
  position: relative;
  background-color: red;
}

/* Fire overlay effect */
.fire::after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: url("@/assets/cell-styles/fire.png") center/contain no-repeat;
  opacity: 0.9;
}

.ashes {
  position: relative;
  background-color: grey;
}

/* Burnt tree overlay effect */
.ashes::after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: url("@/assets/cell-styles/burnt-tree.jpg") center/contain
    no-repeat;
}
</style>
