# blog-jekyll-tool

This project is aimed for those people that has a blog generated with Jekyll tool
it helps to configure your project, create new blog posts and list the current posts
in your folder.

You can create a new project for your post from here: https://github.com/chadbaldwin/simple-blog-bootstrap/generate

After you have your new project you can configure it with this tool.

## Requirements

- Groovy 3.0.6

```shell script
sdk install groovy 3.0.6
```

## Install

For unix users you can install directly with the following commands

- _First the give execution privileges_
```shell script
chmod +x blog.groovy
```
- _Then copy the file to be available anywhere on you terminal_
```shell script
cp blog.groovy /usr/local/bin/blog
```

**Note:** _You can compile this script as NativeImage with GraalVM, check the [BUILD_GRAALVM_IMAGE.md](BUILD_GRAALVM_IMAGE.md)_


## Usage

You can see the options by running the command `-h` or `--help`

```shell script
blog -h

usage: blog -[chnl]
 -c,--configure     Configure user info
 -h,--help          Usage Information
 -n,--new <Title>   Create new blog post with title
 -l,--list          List blog posts created

```

The initial configuration of your blog can start by simply execute the command.

```shell script
blog -c
```

Follow the steps, and then you are ready to create your first blog post using the next command:

```shell script
blog -n "Title of your post"
New post created at [<BLOG_FOLDER>/_posts/2022-05-26-title-of-your-post.md]
```

This will create a new file on your `_posts` folder with the following format 
`yyyy-MM-dd-title-of-your-post.md`

You can list the current `_posts` by doing executing the command

```shell script
blog -l

2021-03-08-blog-post-title-from-file-name.md
2022-05-26-create-a-simple-rest-application-with-spring-boot.md
2022-05-26-how-to-create-your-first-groovy-app.md
2022-05-26-this-is-a-new-blog-post.md

```