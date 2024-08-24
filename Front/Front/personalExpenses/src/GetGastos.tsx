import { useEffect, useState } from "react";
import DeleteGasto from "./DeleteGasto";
import "./GetGastos.css";
import "./App.css";

const getUrl = "http://localhost:8080/gasto/all";

interface Gastos {
  id: number;
  nombreGasto: string;
  montoGasto: number;
  fechaGasto: string;
}

function GastosList({ setFetchGastos }: { setFetchGastos: (callback: () => void) => void }) {
  const [gastos, setGastos] = useState<Gastos[]>([]);

  const fetchGastos = () => {
    fetch(getUrl)
      .then((response) => response.json())
      .then((data) => setGastos(data))
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    fetchGastos();
    setFetchGastos(fetchGastos);
  }, [setFetchGastos]);

  const handleDeleteGasto = (id: number) => {
    DeleteGasto(id, fetchGastos);
  };

  return (
    <div>
      <h1>Gastos</h1>
      <table>
        <thead>
          <tr>
            <th>Tipo de Gasto</th>
            <th>Monto del Gasto</th>
            <th>Fecha Gasto</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {gastos.map((gasto) => (
            <tr key={gasto.id}>
              <td>{gasto.nombreGasto}</td>
              <td>{gasto.montoGasto}</td>
              <td>{gasto.fechaGasto}</td>
              <td>
                <button
                  data-id={gasto.id}
                  onClick={() => handleDeleteGasto(gasto.id)}
                >
                  Eliminar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default GastosList;
