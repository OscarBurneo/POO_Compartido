class Cursos:
    def __init__(self, titulo, duracion, nivel):
        self.titulo = titulo
        self.duracion = duracion
        self.nivel = nivel
    def mostrar_info(self):
        print(f"CUrso: {self.titulo}")
        print(f"Duracion: {self.duracion}")
        print(f"Nivel: {self.nivel}")

class VideoCurso(Cursos):
    def __init__(self, titulo, duracion, nivel, num_video):
        super().__init__(titulo, duracion, nivel)
        self.num_video = num_video
    def mostrar_info(self):
        super().mostrar_info()
        print(f"Cantidad de videos: {self.num_video}")
        print('-' * 30)
class CursoEnVivo(Cursos):
    def __init__(self, titulo, duracion, nivel, fecha):
        super().__init__(titulo,duracion,nivel)
        self.fecha = fecha
    def mostrar_info(self):
        super().mostrar_info()
        print(f"Fecha en vivo: {self.fecha}")
        print('-' * 30)

class CursoLectura(Cursos):
    def __init__(self, titulo, duracion, nivel, paginas):
        super().__init__(titulo,duracion,nivel)
        self.paginas = paginas
    def mostrar_info(self): # metodo personalizados para mostrar la informacion 
        super().mostrar_info() #
        print(f"Numero de Paginas: {self.paginas}")
        print('-'*30)
#creacion de una lista para almacenar los resultados de los objetos
cursos = [
    VideoCurso('Curso de Python', 20, "Basico", 3),
    CursoEnVivo('Fndamentos de base de datos', 30, 'Intermedio','12-06-2025'),
    CursoLectura('Fundamentos de IA', 15, 'Experto',35)
]
print("Informacion de los cursos de la plataforma")
for curso in cursos:
    curso.mostrar_info()