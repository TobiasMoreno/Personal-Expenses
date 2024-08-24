import React, { useState, useEffect } from "react";
import "./PostGasto.css";

const urlPost = "http://localhost:8080/gasto/create";
const getSelect = "http://localhost:8080/gasto/nombres-gasto";

interface TipoGastos {
  id: number;
  nombreGasto: string;
}

function GastoSelect({
  onSelectGasto,
}: {
  onSelectGasto: (gasto: TipoGastos) => void;
}) {
  const [nombresGasto, setNombresGasto] = useState<TipoGastos[]>([]);

  useEffect(() => {
    fetch(getSelect)
      .then((response) => response.json())
      .then((data) => setNombresGasto(data))
      .catch((err) => console.error(err));
  }, []);

  const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const selectedId = Number(e.target.value);
    const selectedGasto = nombresGasto.find((gasto) => gasto.id === selectedId);
    if (selectedGasto) {
      onSelectGasto(selectedGasto);
    }
  };

  return (
    <div className="post-gasto">
      <label>Seleccione un tipo de Gasto:</label>
      <select onChange={handleSelectChange} className="select-post">
        <option value="">Seleccione un gasto</option>
        {nombresGasto.map((gasto) => (
          <option value={gasto.id} key={gasto.id}>
            {gasto.nombreGasto}
          </option>
        ))}
      </select>
    </div>
  );
}

function CreateGasto({ fetchGastos }: { fetchGastos: () => void }) {
  const [tipoGasto, setTipoGasto] = useState<TipoGastos | null>(null);
  const [montoGasto, setMontoGasto] = useState<number>(0);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (!tipoGasto) {
      alert("Por favor, seleccione un tipo de gasto.");
      return;
    }

    const nuevoGasto = {
      tipoGasto: {
        id: tipoGasto.id,
        nombreGasto: tipoGasto.nombreGasto,
      },
      montoGasto,
    };

    fetch(urlPost, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(nuevoGasto),
    })
      .then((response) => response.json())
      .then(() => {
        setMontoGasto(0);
        setTipoGasto(null);
        fetchGastos();
      })
      .catch((err) => console.error(err));
  };

  return (
    <div>
      <h2>Agregar Nuevo Gasto</h2>
      <form onSubmit={handleSubmit} className="post-gasto">
        <GastoSelect onSelectGasto={setTipoGasto} />
        <label>Monto a Agregar</label>
        <input
          type="number"
          value={montoGasto}
          onChange={(e) => setMontoGasto(Number(e.target.value))}
          placeholder="Monto del Gasto"
          required
          min={0}
        />
        <button type="submit" className="button-post">
          Agregar Gasto
        </button>
      </form>
    </div>
  );
}

export default CreateGasto;
