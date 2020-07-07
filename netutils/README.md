## Набор базовых классов и методов для работы с сетью

#### Добавление к проекту
```bash
git remote add netutils-remote git@gitlab.65apps.com:akorobeynikov/netutils.git
git subtree add --prefix netutils --squash netutils-remote netutils
```
Добавить модуль в `<project_root>/settings.gradle`
```
include ':netutils'
```
Прописать версии зависимостей в соответствии с инструкцией
[Синхронизация зависимостей в многомодульных проектах](https://wiki.65apps.com/pages/viewpage.action?pageId=6951074)
