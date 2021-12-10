# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.dreamers.popupwindow.PopupWindow {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/dreamers/popupwindow/repack'
-flattenpackagehierarchy
-dontpreverify

# Ignore Java Lamba Warnings
-dontwarn java.lang.invoke.LambdaMetafactory