interface Gestion<T> {
    void agregarDesdeTexto(String texto);
    String convertirATexto(T elemento);
    Iterable<T> obtenerTodos();
}
