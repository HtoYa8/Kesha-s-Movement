# Инструкции по компиляции Kesha's BetterMovement Mod

## ⚠️ Важно: О компиляции в Replit

Этот проект - это **мод для Minecraft**, а не веб-приложение. Он требует компиляции в .jar файл, который затем используется в Minecraft.

**Компиляция в Replit может иметь ограничения** из-за специфических требований Fabric API и Minecraft Development Kit.

## 🎯 Рекомендуемый способ: Компиляция локально

### Шаг 1: Установите необходимые инструменты

1. **Java 21**:
   - Скачайте с [Adoptium](https://adoptium.net/)
   - Или с [Oracle](https://www.oracle.com/java/technologies/downloads/)

2. **Gradle** (опционально, включён в проект):
   - Gradle Wrapper уже включён (gradlew / gradlew.bat)

### Шаг 2: Скачайте проект

```bash
# Клонируйте или скачайте проект
git clone <URL_вашего_репозитория>
cd bettermovement
```

### Шаг 3: Компиляция

**На Linux/macOS:**
```bash
./gradlew build
```

**На Windows:**
```cmd
gradlew.bat build
```

### Шаг 4: Найдите скомпилированный мод

Файл будет находиться в:
```
build/libs/bettermovement-1.0.0.jar
```

## 🔧 Компиляция в Replit (альтернативный способ)

Если вы всё же хотите попробовать скомпилировать в Replit:

### 1. Убедитесь, что Java 21 установлена

Используйте панель инструментов Replit для установки Java 21, или выполните:

```bash
nix-shell -p jdk21 gradle
```

### 2. Установите переменные окружения

```bash
export JAVA_HOME=$(nix-build '<nixpkgs>' -A jdk21 --no-out-link)
export PATH=$JAVA_HOME/bin:$PATH
```

### 3. Запустите компиляцию

```bash
./gradlew build --no-daemon
```

### 4. Проверьте результат

```bash
ls -lh build/libs/
```

## 📦 Установка мода в Minecraft

1. Убедитесь, что у вас установлен **Fabric Loader для Minecraft 1.21.1**
2. Скачайте **Fabric API** с [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api) или [Modrinth](https://modrinth.com/mod/fabric-api)
3. Скопируйте файл `bettermovement-1.0.0.jar` в папку `.minecraft/mods/`
4. Запустите Minecraft 1.21.1 с Fabric

## 🎮 Использование

- Нажмите **B** в игре для переключения мода
- Попробуйте зажать W и затем S - персонаж пойдёт назад!
- Настройте горячую клавишу в Настройки → Управление → Kesha's BetterMovement

## ❓ Решение проблем

### Ошибка: "JAVA_HOME is not set"
```bash
export JAVA_HOME=/путь/к/java21
```

### Ошибка: "Could not resolve dependencies"
- Проверьте подключение к интернету
- Попробуйте: `./gradlew build --refresh-dependencies`

### Мод не работает в игре
- Убедитесь, что версия Minecraft точно 1.21.1
- Проверьте, что Fabric Loader установлен
- Проверьте, что Fabric API находится в папке mods

## 📝 Структура проекта

```
bettermovement/
├── src/main/java/          # Исходный код мода
├── src/main/resources/     # Ресурсы и конфигурация
├── build.gradle            # Конфигурация сборки
├── gradle.properties       # Параметры проекта
└── build/libs/            # Скомпилированные .jar файлы
```

## 🤝 Поддержка

Если возникли проблемы с компиляцией, проверьте:
1. Версия Java = 21
2. Интернет-соединение активно
3. Файлы проекта не повреждены

---

**Удачной игры! 🎮**
