const deleteUrl = "http://localhost:8080/gasto";

async function DeleteGasto(id: number, OnSuccess: () => void): Promise<void> {
  if (window.confirm("¿Seguro desea eliminar este gasto?")) {
    fetch(`${deleteUrl}/${id}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (response.ok) {
          alert("Eliminado exitosamente");
          OnSuccess();
        } else {
          alert("Error al eliminar el gasto");
        }
      })
      .catch((err) => console.error(err));
  }
}

export default DeleteGasto;
