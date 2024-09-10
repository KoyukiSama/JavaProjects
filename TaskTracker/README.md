## project

https://roadmap.sh/projects/task-tracker
here's where I got my project from

when I finish this version I think it'd be cool for me to expand upon it and make it a full on cli program.

## how to use command
```
Syntax: task-cli ... ...
task-cli                         -- displays Syntax information
add "description"                -- add a task, with a description
update id "description"          -- update an already existing task, id can be found with list command
delete id                        -- delete a task

mark-in-progress id              -- mark a task to in-progress
mark-done id                     -- mark a task as done
mark-todo id                     -- mark a task as todo

list                             -- lists  {id  status  description  updatedTime creationTime}
list in-progress                 -- lists in-progress only
list todo                        -- lists todo only
list in-progress                 -- lists in-progress only
```
## installing on linux pc
you can create a file in your local binaries to execute this.

file name: task-cli
```
#!/bin/bash
java -jar /path/to/task-cli.jar "$@"
```

and then it should be usable
