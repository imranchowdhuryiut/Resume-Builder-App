package com.example.resumebuilder.utils

/**
 * Created by Imran Chowdhury on 2/19/2022.
 */
interface OnItemClickCallback<Model> {
    fun onClick(model: Model)
}

interface OnResumeClickCallback<Model> {
    fun onDelete(model: Model)
    fun onEdit(model: Model)
}