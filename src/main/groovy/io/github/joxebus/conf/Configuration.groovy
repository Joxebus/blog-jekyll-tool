package io.github.joxebus.conf

class Configuration {
    static final String HOME_FOLDER = System.getProperty("user.home") + File.separator + "blog-config"
    static final String BLOG_FOLDER = System.getenv("BLOG_FOLDER")
    static final File homeFolder = new File(HOME_FOLDER)

    static final List<String> configurations = ["title", "author", "description", "email", "twitter_username", "github_username", "linkedin_username"]
    static final Map<String, String> settings = ["editor":"Command for editor: ", "open_on_create": "Open the file after created? y/n: ", "author": "Your name here: ", "tags": "Default tags separated by comma: "]

    static final String dateFormat = "yyyy-MM-dd"
    static final String dateFormatDetail = "yyyy-MM-dd HH:mm"
    static final String blogPostTemplate = '''---
title:      "$title"
subtitle:   "Something that describes your post"
date:       $date
author:     "$author"
header-img: "/your/image/here"
category:   techblog
tags:       [$tags]
---

## $title

You can start writing here in Markdown format https://www.markdownguide.org/basic-syntax/
'''

}
