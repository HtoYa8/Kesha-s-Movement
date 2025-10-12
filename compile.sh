#!/bin/bash

echo "===================================="
echo "Kesha's BetterMovement Mod Builder"
echo "===================================="
echo ""

if [ -z "$JAVA_HOME" ]; then
    echo "⚠️  JAVA_HOME is not set!"
    echo ""
    echo "Для компиляции в Replit:"
    echo "1. Установите Java 21 через нижнюю панель инструментов (Tools -> Packages)"
    echo "2. Или используйте команду: nix-shell -p jdk21"
    echo ""
    echo "Для компиляции локально:"
    echo "1. Установите Java 21: https://adoptium.net/"
    echo "2. Установите Gradle 8.x: https://gradle.org/install/"
    echo "3. Запустите: ./gradlew build"
    echo ""
    echo "Файл мода будет находиться в build/libs/"
    exit 1
fi

echo "✓ JAVA_HOME: $JAVA_HOME"
echo "✓ Java version: $(java -version 2>&1 | head -1)"
echo ""
echo "Запуск компиляции..."
echo ""

./gradlew build --no-daemon

if [ $? -eq 0 ]; then
    echo ""
    echo "✅ Компиляция успешна!"
    echo ""
    echo "Файл мода:"
    ls -lh build/libs/*.jar 2>/dev/null || echo "build/libs/bettermovement-1.0.0.jar"
    echo ""
    echo "Скопируйте .jar файл в папку mods вашего Minecraft!"
else
    echo ""
    echo "❌ Компиляция не удалась. Проверьте логи выше."
    exit 1
fi
