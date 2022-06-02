package io.github.joxebus

import groovy.transform.CompileStatic
import groovy.transform.stc.POJO
import io.github.joxebus.conf.Configuration
import io.github.joxebus.service.CommandService

@POJO
@CompileStatic
class Application {

    static void main(String[] args) {
        CommandService commandService = new CommandService()

        if (!args || args.length > 2) {
            println "Invalid arguments, please see the usage section: "
            commandService.usage()
            System.exit(0)
        }

        if(!Configuration.BLOG_FOLDER) {
            println "You must setup your env var BLOG_FOLDER first"
            System.exit(1)
        }


        if(!Configuration.homeFolder.exists()) {
            println "Creating home folder [${Configuration.HOME_FOLDER}]"
            Configuration.homeFolder.mkdirs()
            commandService.configureSettings()
        }

        if (["-h", "--help"].contains(args[0])) {
            commandService.usage()
            System.exit(0)
        }

        if(["-s", "--settings"].contains(args[0])) {
            commandService.configureSettings()
            System.exit(0)
        }

        if(["-c", "--configure"].contains(args[0])) {
            commandService.configure()
            System.exit(0)
        }

        if(["-n", "--new"].contains(args[0])) {
            if(args.length < 2) {
                println "You must to specify a Title with the command -n or --new"
                commandService.usage()
                System.exit(1)
            }
            commandService.newPost(args[1])
            System.exit(0)
        }

        if(["-l", "--list"].contains(args[0])) {
            commandService.listPosts()
            System.exit(0)
        }
    }
}
