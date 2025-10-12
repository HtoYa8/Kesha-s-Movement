#!/bin/bash

clear

cat << 'EOF'
╔══════════════════════════════════════════════════════════╗
║                                                          ║
║        🎮 Kesha's BetterMovement Minecraft Mod 🎮        ║
║                                                          ║
╚══════════════════════════════════════════════════════════╝

📋 ИНФОРМАЦИЯ О МОДЕ:

   Название: Kesha's BetterMovement
   Версия: 1.0.0
   Minecraft: 1.21.1
   Loader: Fabric

📖 ОПИСАНИЕ:

   Мод изменяет логику клавиш движения в Minecraft:
   
   ✨ Последняя нажатая клавиша ПЕРЕБИВАЕТ предыдущую
   ✨ W + S = движение назад (приоритет S)
   ✨ A + D = движение вправо (приоритет D)
   
🎯 ВОЗМОЖНОСТИ:

   ✅ Переключение мода клавишей B
   ✅ Сохранение настроек
   ✅ Локализация RU/EN
   ✅ Настраиваемая горячая клавиша

📁 СТРУКТУРА ПРОЕКТА:
EOF

echo ""
ls -lh src/main/java/com/kesha/bettermovement/*.java 2>/dev/null | awk '{print "   📄 " $9}' | sed 's|src/main/java/com/kesha/bettermovement/||g'
echo ""
ls -lh src/main/java/com/kesha/bettermovement/mixin/*.java 2>/dev/null | awk '{print "   📄 " $9}' | sed 's|src/main/java/com/kesha/bettermovement/mixin/||g' | sed 's|^|   └── Mixin: |'

cat << 'EOF'

🔨 КОМПИЛЯЦИЯ:

   ⚠️  Компиляция в Replit имеет ограничения!
   
   Рекомендуется ЛОКАЛЬНАЯ компиляция:
   
   1. Установите Java 21
   2. Выполните: ./gradlew build
   3. Возьмите: build/libs/bettermovement-1.0.0.jar

📚 ДОКУМЕНТАЦИЯ:

   📖 README.md - основная информация
   📖 COMPILE_INSTRUCTIONS.md - инструкции по компиляции
   📖 replit.md - документация проекта

🚀 УСТАНОВКА В MINECRAFT:

   1. Установите Fabric Loader 1.21.1
   2. Скачайте Fabric API
   3. Поместите .jar в папку mods/
   4. Запустите Minecraft!

💡 ИСПОЛЬЗОВАНИЕ:

   • Запустите Minecraft 1.21.1 с Fabric
   • Нажмите B для включения/выключения мода
   • Попробуйте W + S - персонаж пойдёт назад!
   • Настройте клавишу в Настройки → Управление

╔══════════════════════════════════════════════════════════╗
║                                                          ║
║         Проект готов к компиляции! 🎉                    ║
║                                                          ║
║         Читайте COMPILE_INSTRUCTIONS.md                  ║
║                                                          ║
╚══════════════════════════════════════════════════════════╝

EOF

echo ""
read -p "Нажмите Enter для выхода..."
