package com.yurylink.decisiontree.functions;

import com.yurylink.decisiontree.common.model.TreeContext;

import java.util.function.Function;

@FunctionalInterface
interface NodeFunction<M extends TreeContext, R extends String> extends Function<M, R> {

}
