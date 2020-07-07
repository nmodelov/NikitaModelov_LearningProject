## Android библиотека с набором базовых классов и методов для реализации приложения на базе MVIKotlin

#### Добавление к проекту
```bash
git remote add mvi-remote git@gitlab.65apps.com:akorobeynikov/mvi-common.git
git subtree add --prefix mvi --squash mvi-remote mvi-common
```
Добавить модуль в `<project_root>/settings.gradle`
```
include ':mvi'
```
Прописать версии зависимостей в соответствии с инструкцией
[Синхронизация зависимостей в многомодульных проектах](https://wiki.65apps.com/pages/viewpage.action?pageId=6951074)
