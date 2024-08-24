import { useEffect, useState } from "react";
import { PieChart, Pie, Cell, Tooltip, Legend } from "recharts";

interface GastoTotal {
  nombreGasto: string;
  montoTotal: number;
}

const COLORS = ["#0088FE", "#00C49F", "#FFBB28", "#FF8042", "#FF6384"];

const [gastoTotales, setGastoTotales] = useState<GastoTotal[]>([]);

function graphic() {
    
  const calcularTotalesPorTipo = (gastos: Gastos[]) => {
    const totales = gastos.reduce((acc: GastoTotal[], gasto) => {
      const index = acc.findIndex(
        (item) => item.nombreGasto === gasto.nombreGasto
      );
      if (index >= 0) {
        acc[index].montoTotal += gasto.montoGasto;
      } else {
        acc.push({
          nombreGasto: gasto.nombreGasto,
          montoTotal: gasto.montoGasto,
        });
      }
      return acc;
    }, []);

    setGastoTotales(totales);
  };

  return (
    <div>
      <h2>Gasto Total por Tipo</h2>
      <PieChart width={400} height={400}>
        <Pie
          data={gastoTotales}
          dataKey="montoTotal"
          nameKey="nombreGasto"
          cx="50%"
          cy="50%"
          outerRadius={150}
          fill="#8884d8"
          label
        >
          {gastoTotales.map((_, index) => (
            <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
          ))}
        </Pie>
        <Tooltip />
        <Legend />
      </PieChart>
    </div>
  );
}
