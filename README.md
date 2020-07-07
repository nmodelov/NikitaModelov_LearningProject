## Расширения для MVIKotlin, добавляющие поддержку RxJava3

#### Добавление к проекту
```bash
git remote add mvikotlinrxjava3-remote git@gitlab.65apps.com:akorobeynikov/mvi-kotlin-rxjava3.git
git subtree add --prefix mvikotlinrxjava3 --squash mvikotlinrxjava3-remote mvi-kotlin-rxjava3
```
Добавить модуль в `<project_root>/settings.gradle`
```
include ':mvikotlinrxjava3'
```
Прописать версии зависимостей в соответствии с инструкцией
[Синхронизация зависимостей в многомодульных проектах](https://wiki.65apps.com/pages/viewpage.action?pageId=6951074)
