import com.example.site.ResourceMapper
import com.example.site.taglib.ThemeTagLib

environments {
    dev {
        log.info 'Development environment is used'
        jetty_port = 4000
        url = "http://localhost:${jetty_port}"
        show_unpublished = true
    }
    prod {
        log.info 'Production environment is used'
        url = '' // site URL, for example http://www.example.com
        show_unpublished = false
        features {
            minify_xml = false
            minify_html = false
            minify_js = false
            minify_css = false
        }
    }
    cmd {
        features {
            compass = 'none'
            highlight = 'none'
        }
    }
}

// Default features configuration.
features {
    compass = 'none'             // 'none', 'ruby', 'jruby', 'shell', 'auto'
    highlight = 'pygments'       // 'none', 'pygments'
    // pygments = 'auto'         // 'none', 'python', 'jython', 'shell', 'auto'
    // cache_highlight = 'true'  // default to 'true'
}

// Resource mapper and tag libs.
resource_mapper = new ResourceMapper(site).map
tag_libs = [ThemeTagLib]

// Locale and datetime format.
Locale.setDefault(Locale.US)
datetime_format = 'yyyy-MM-dd HH:mm'

// Site directories.
base_dir = System.getProperty('user.dir')
cache_dir = "${base_dir}/.cache"
content_dir = "${base_dir}/content"
theme_dir = "${base_dir}/theme"
source_dir = [content_dir, theme_dir, "${cache_dir}/compass"]
include_dir = "${theme_dir}/includes"
layout_dir = "${theme_dir}/layouts"
destination_dir = "${base_dir}/target"

excludes = ['/sass/.*', '/src/.*', '/target/.*']

// Deployment settings.
s3_bucket = '' // your S3 bucket name
deploy = "s3cmd sync --acl-public --reduced-redundancy ${destination_dir}/ s3://${s3_bucket}/"

// Custom commands-line commands.
commands = [
/**
 * Creates new page.
 *
 * location - relative path to the new page or to the directory to save a new page to, should start with the /, i.e.
 *            /pages/page.md. If contains file extension, command will attempt to create file as specified in this
 *            variable, otherwise command will create index.markdown file in the specified directory
 * pageTitle - new page title
 */
'create-page': { String location, String pageTitle ->
        def ext = new File(location).extension
        def file = ext ? file = new File(content_dir, location) : new File(content_dir + location, 'index.markdown')
        file.parentFile.mkdirs()
        file.exists() || file.write("""---
layout: default
title: "${pageTitle}"
published: true
---
""")}
]

// Theme configuration

/*
 * The delimiter for the items on the left side of the related bar.
 * This defaults to ' &raquo;' Each item in the related bar ends
 * with the value of this variable.
 */
reldelim1 = ' &raquo;'

/*
 * The delimiter for the items on the right side of the related bar.
 * This defaults to ' |'. Each item except of the last one in the
 * related bar ends with the value of this variable.
 */
reldelim2 = ' |'

/*
 * Width of the sidebar in pixels. (Do not include px in the value.)
 * Defaults to 230 pixels.
 */
sidebarwidth = 230

/*
 * Put the sidebar on the right side. Defaults to false.
 */
rightsidebar = false

/*
 * Make the sidebar “fixed” so that it doesn’t scroll out of view for
 * long body content. This may not work well with all browsers.
 * Defaults to false.
 */
stickysidebar = false

/*
 * Add an experimental JavaScript snippet that makes the sidebar
 * collapsible via a button on its side. Doesn’t work
 * with “stickysidebar”. Defaults to false.
 */
collapsiblesidebar = false

/*
 * Display external links differently from internal links. Defaults to false.
 */
externalrefs = false

/*
 * There are also various color and font options that can change the color
 * scheme without having to write a custom stylesheet:
 */
footerbgcolor    = '#11303d' // Background color for the footer line
footertextcolor  = '#ffffff' // Text color for the footer line.
sidebarbgcolor   = '#1c4e63' // Background color for the sidebar
sidebarbtncolor  = '#3c6e83' // Background color for the sidebar collapse button (used when collapsiblesidebar is true).
sidebartextcolor = '#ffffff' // Text color for the sidebar
sidebarlinkcolor = '#98dbcc' // Link color for the sidebar
relbarbgcolor    = '#133f52' // Background color for the relation bar
relbartextcolor  = '#ffffff' // Text color for the relation bar
relbarlinkcolor  = '#ffffff' // Link color for the relation bar
bgcolor          = '#ffffff' // Body background color
textcolor        = '#000000' // Body text color
linkcolor        = '#355f7c' // Body link color
visitedlinkcolor = '#355f7c' // Body color for visited links
headbgcolor      = '#f2f2f2' // Background color for headings
headtextcolor    = '#20435c' // Text color for headings
headlinkcolor    = '#c60f0f' // Link color for headings
codebgcolor      = '#eeffcc' // Background color for code blocks
codetextcolor    = '#333333' // Default text color for code blocks, if not set differently by the highlighting style

// Font for normal text
bodyfont = 'sans-serif'

// Font for headings
headfont = "'Trebuchet MS', sans-serif"
