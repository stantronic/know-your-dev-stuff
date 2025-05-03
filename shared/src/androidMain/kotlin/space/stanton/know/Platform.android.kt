@file:Suppress("unused")

package space.stanton.know

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}



