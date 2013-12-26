package com.example.site.taglib

import com.sysgears.grain.taglib.GrainTagLib

class ThemeTagLib {

    /**
     * Grain taglib reference.
     */
    private GrainTagLib taglib

    public ThemeTagLib(GrainTagLib taglib) {
        this.taglib = taglib
    }

    /**
     * Returns relative path from the page to root of the site
     * 
     * @attr location page location
     */
    def pathToRoot = { String location ->
        '../' * (location.count('/') - 1)
    }

    /**
     * Generates html tag for an image
     *
     * @attr location image location
     * @attr width (optional) image width
     * @attr height (optional) image height
     */
    //def img = { String location, Integer width = null, Integer height = null ->
    //    def widthStr = width ? " width=\"${width}\"" : ""
    //    def heightStr = height ? " height=\"${height}\"" : ""
    //
    //    "<img${widthStr}${heightStr} src=\"${taglib.r(location)}\" alt=\"image\">"
    //}
}
