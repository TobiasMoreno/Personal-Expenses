import React, { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import GastosList from "./GetGastos.tsx";
import CreateGasto from "./PostGasto.tsx";
import "./index.css";

function Gastos() {
  const fetchGastos = React.useRef<() => void>();

  const setFetchGastos = (callback: () => void) => {
    fetchGastos.current = callback;
  };

  return (
    <div>
      <CreateGasto fetchGastos={() => fetchGastos.current?.()} />
      <GastosList setFetchGastos={setFetchGastos} />
    </div>
  );
}

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <div className="container">
      <Gastos/>
    </div>
  </StrictMode>
);
