package io.github.joxebus

import groovy.cli.picocli.CliBuilder
import groovy.cli.picocli.OptionAccessor
import io.github.joxebus.conf.Configuration
import io.github.joxebus.service.CommandLineService

class Application {

    static CliBuilder buildCli(String[] args) {
        CliBuilder cli = new CliBuilder(usage: "blog -[hscnld]")

        cli.with {
            h longOpt: 'help', 'Usage Information'
            s longOpt: 'settings', 'Configure settings of the command line'
            c longOpt: 'configure', 'Configure local user info'
            n longOpt: 'new', args: '1', argName: 'title', 'Create new blog post with title'
            l longOpt: 'list', 'List blog posts created'
            d longOpt: 'delete', args: '1', argName: 'file', 'Deletes a blog post'
        }

        cli
    }

    static void main(String[] args) {
        CliBuilder cli = buildCli(args)
        CommandLineService commandService = new CommandLineService()
        try {

            OptionAccessor options = cli.parse(args)

            if(!Configuration.BLOG_FOLDER) {
                println "You must setup your env var BLOG_FOLDER first"
                System.exit(1)
            }


            if(!Configuration.homeFolder.exists()) {
                println "Creating home folder [${Configuration.HOME_FOLDER}]"
                Configuration.homeFolder.mkdirs()
                commandService.configureSettings()
            }

            if(!options) {
                System.exit(1)
            }

            if (options.h) {
                cli.usage()
                System.exit(0)
            }

            if(options.s) {
                commandService.configureSettings()
                System.exit(0)
            }

            if(options.c) {
                commandService.configure()
                System.exit(0)
            }

            if(options.n) {
                if(args.length < 2) {
                    println "You must to specify a Title with the command -n or --new"
                    commandService.usage()
                    System.exit(1)
                }
                commandService.newPost(options.n)
                System.exit(0)
            }

            if(options.l) {
                commandService.listPosts()
                System.exit(0)
            }

            if(options.d) {
                commandService.delete(options.d)
                System.exit(0)
            }
        } catch(RuntimeException e) {
            cli.usage()
        }


    }
}
