# blog-jekyll-tool

This project is aimed for those people that has a blog generated with Jekyll tool
it helps to configure your project, create new blog posts and list the current posts
in your folder.

You can create a new project for your post from here: https://github.com/chadbaldwin/simple-blog-bootstrap/generate

After you have your new project you can configure it with this tool.

## Requirements

- Java 11+
- Groovy 4.0.2

```shell script
sdk install groovy 4.0.2
```

## Install

For unix users you can install directly with the following commands

### Installation script
```shell script
./install.sh
```
### Maven
```shell script
mvn verify -Punix
```

**Note:** There is no profile for Windows systems for now.


## Usage

You can see the options by running the command `-h` or `--help`

```shell script
blog -h

usage: blog -[schnl]
 -s,--settings      Configure settings of the command line
 -c,--configure     Configure user info
 -h,--help          Usage Information
 -n,--new <Title>   Create new blog post with title
 -l,--list          List blog posts created

```

The initial configuration of your blog can start by simply execute the command.

```shell script
blog -c
```

You can modify the settings of the tool with the command `-s` or `--settings` this will create
a file on your `HOME_USER/blog-config/settings.yml` like this.

```yaml
editor: "idea"
open_on_create: "y"
author: "Omar Bautista"
tags: "joxebus, programming, java, groovy, desveloper"
```

Follow the steps, and then you are ready to create your first blog post using the next command:

```shell script
blog -n "Title of your post"
New post created at [<BLOG_FOLDER>/_posts/2022-05-26-title-of-your-post.md]
```

This will create a new file on your `_posts` folder with the following format 
`yyyy-MM-dd-title-of-your-post.md`

_**Sample file generated with the tool**_
```markdown
---
title:      "Title of your post"
subtitle:   "Something that describes your post"
date:       2022-05-27 20:34
author:     "Omar Bautista"
header-img: "/your/image/here"
category:   techblog
tags:       [joxebus, programming, java, groovy, desveloper]
---

## Title of your post

You can start writing here in Markdown format https://www.markdownguide.org/basic-syntax/
```

You can list the current `_posts` by doing executing the command

```shell script
blog -l

2021-03-08-blog-post-title-from-file-name.md
2022-05-26-create-a-simple-rest-application-with-spring-boot.md
2022-05-26-how-to-create-your-first-groovy-app.md
2022-05-26-this-is-a-new-blog-post.md

```