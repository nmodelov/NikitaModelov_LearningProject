## Расширения для MVIKotlin, добавляющие поддержку RxJava2

#### Добавление к проекту
```bash
git remote add mvikotlinrxjava2-remote git@gitlab.65apps.com:akorobeynikov/mvi-kotlin-rxjava2.git
git subtree add --prefix mvikotlinrxjava2 --squash mvikotlinrxjava2-remote mvi-kotlin-rxjava2
```
Добавить модуль в `<project_root>/settings.gradle`
```
include ':mvikotlinrxjava2'
```
Прописать версии зависимостей в соответствии с инструкцией
[Синхронизация зависимостей в многомодульных проектах](https://wiki.65apps.com/pages/viewpage.action?pageId=6951074)
