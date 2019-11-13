package me.aartikov.fruitcounter.presentation.utils.localized_string

import android.content.Context
import android.widget.TextView

fun LocalizedString.get(context: Context): String = when (this) {
    LocalizedString.EmptyString -> ""
    is LocalizedString.RawString -> value
    is LocalizedString.ResourceString -> {
        context.getString(resourceId, *getArgValues(context, args))
    }
    is LocalizedString.QuantityResourceString -> {
        context.resources.getQuantityString(resourceId, quantity, *getArgValues(context, args))
    }
    is LocalizedString.CompoundString -> {
        parts.joinToString(separator = "") { it.get(context) }
    }
}

var TextView.localizedText: LocalizedString
    get() = LocalizedString.raw(text.toString())
    set(value) {
        text = value.get(this.context)
    }

private fun getArgValues(context: Context, args: List<Any>): Array<Any> {
    return args.map {
        if (it is LocalizedString) {
            it.get(context)
        } else {
            it
        }
    }.toTypedArray()
}