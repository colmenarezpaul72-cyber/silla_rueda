# Silla de ruedas — Fabric 1.21.1

Proyecto inicial para Minecraft Java 1.21.1 (Fabric / Java 21).

## Incluye
- Modelo original `modelo_original.bbmodel` y textura PNG extraída.
- Entidad silla que permite montarse con clic derecho, y desmontarse con Shift (comportamiento vanilla).
- Desaparece al recibir daño.
- GitHub Actions para compilar y publicar el JAR como Artifact.

## Compilar localmente
Instala Java 21 y Gradle 8.x; en la carpeta ejecuta `gradle build`. El JAR queda en `build/libs/`.

## Compilar en GitHub
Sube el contenido del ZIP a un repositorio, abre **Actions → Build mod jar → Run workflow**. Descarga `silla-de-ruedas-jar` al terminar.

## Nota importante sobre el modelo
El `.bbmodel` y la textura se conservan. Este starter todavía no convierte/renderiza automáticamente la geometría y animación `rodar` de Blockbench dentro del juego; la entidad puede ser invisible hasta añadir el renderer/modelo exportado compatible. La interacción/montaje está preparada como base.
