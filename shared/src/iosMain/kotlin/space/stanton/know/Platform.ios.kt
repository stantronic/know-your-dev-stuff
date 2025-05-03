@file:Suppress("unused")

package space.stanton.know

import platform.UIKit.UIDevice

class IosPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

