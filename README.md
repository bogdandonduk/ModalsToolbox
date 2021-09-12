
# ModalsToolbox 
  
  Android library based on the enhanced builder pattern that simplifies creation of modals that look beautiful. It currently supports bottom sheet modals.
  ![Example](https://github.com/bogdandonduk/ModalsToolbox/blob/master/device-2021-09-12-205941.png)
  
## Include in your project  
**Gradle dependency**  
  
Add this in your **app**-level **build.gradle** file:  
```groovy  
dependencies {  
	...  
  
	def latest_version_tag = 2.1
	implementation "com.github.bogdandonduk:ModalsToolbox:$latest_version_tag"  
  
	...  
}  
```  
You can always find the **latest_version_tag** [here](https://github.com/bogdandonduk/AppManualToolbox/releases).  
  
Also make sure you have this repository in your **project**-level **build.gradle** file:  
```groovy  
allprojects {  
	repositories {  
		...  
  
		maven { url 'https://jitpack.io' }  
	}  
}  
```  

# Examples of usage
```kotlin 
// get a builder for simple text-containing bottom sheet modal, initialize it and show: 
val builder = ModalsToolbox.buildSimple()

builder
	.setAppearance { oldAppearance: Appearance -> // this is a feature of the enhanced builder pattern,
	// it provides previous values as lambda arguments for you to operate on.
	// just return a new value from this lambda to set it
	// p.s. Do not worry about performance, this lambda is inlined.
		Appearance(
			backgroundColor = Color.WHITE
		)
	}
	.setTitle { oldTitle: Title ->
		Title(
			text = "Storage permission required",
			textColor = Color.BLACK
		)
	}
	.setAllTextContent {
		mutableListOf<Text>().apply {
			add(
				Text(
					text = "This is required to access files on your device",
					textColor = Color.BLACK
				)
			)
		}
	}
	.setPositiveButton { oldButton: Button ->
		Button(
			text = "Allow",
			textColor = Color.BLACK
		)
	}
	.setNegativeButton { oldButton: Button ->
		Button(
			text = "Deny",
			textColor = Color.RED
		)
	}
	.setCallbacks { oldCallbacks ->
		Callbacks(
			onCancelAction = { rootView: View, modal: BottomSheetDialogFragment ->
				onBackPressed()
			}
		)
	}
	.show(supportFragmentManager)
```
