package io.github.joxebus.service

import groovy.transform.CompileStatic

@CompileStatic
class CommandService {

    ConfigurationService configurationService = new ConfigurationService()
    PostService postService = new PostService()

    static final String USAGE = """
usage: blog -[schnld]
 -s,--settings      Configure settings of the command line
 -c,--configure     Configure user info
 -h,--help          Usage Information
 -n,--new <Title>   Create new blog post with title
 -l,--list          List blog posts created
 -d,--delete <file> Deletes a blog post
"""

    void usage() {
        println USAGE
    }

    void configureSettings() {
        configurationService.configureSettings()
    }

    void configure() {
        configurationService.configure()
    }

    void newPost(String title) {
        postService.newPost(title)
    }

    void listPosts() {
        postService.listPosts()
    }

    void delete(String filename) {
        postService.deletePost(filename)
    }
}
