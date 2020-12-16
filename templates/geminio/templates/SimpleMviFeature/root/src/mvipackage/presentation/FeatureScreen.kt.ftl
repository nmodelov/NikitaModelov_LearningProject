package ${packageName}.presentation.${featurePackage}

import com.github.terrakok.cicerone.androidx.FragmentScreen

fun ${featureName?lowerCase}Screen() = FragmentScreen {
    ${featureName}Fragment.newInstance()
}
