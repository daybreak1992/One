package model

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
data class SearchHeader(var banners_title: Result<List<Banner>>?, var banners_horizontal: Result<List<Banner>>?,
                   var hot_authors: Result<List<User>>?) {

    constructor() : this(null, null, null)
}