dojo.require("dojo.string.Builder");

function test_Builder_ctor(){
	var a = new dojo.string.Builder();
	jum.assertEquals("test10", "", a.toString());
	var b = new dojo.string.Builder("foo");
	jum.assertEquals("test20", "foo", b.toString());
	jum.assertEquals("test30", "foo", b.valueOf());

}

function test_Builder_ctorComplex(){
	var c = new dojo.string.Builder("foo", "bar", ["baz", "thud"]);
	jum.assertEquals("test31", "foobarbazthud", c.valueOf());
	jum.assertEquals("test32", "foobarbazthud".length, c.length);
}

function test_Builder_append(){
	var b = new dojo.string.Builder("foo");
	b.append("bar");
	jum.assertEquals("test40", "foobar", b.valueOf());
	b.append(" baz");
	jum.assertEquals("test50", "foobar baz", b.toString());
}

function test_Builder_concat(){
	var b = new dojo.string.Builder("foo");
	b.concat("bar");
	jum.assertEquals("test40", "foobar", b.valueOf());
	b.concat(" baz");
	jum.assertEquals("test50", "foobar baz", b.toString());
}

function test_Builder_appendComplex(){
	var b = new dojo.string.Builder("foo");
	b.append("bar", "baz");
	jum.assertEquals("test41", "foobarbaz", b.valueOf());
	b.append([" thud", " quux"]);
	jum.assertEquals("test42", "foobarbaz thud quux", b.toString());
}

function test_Builder_concatComplex(){
	var b = new dojo.string.Builder("foo");
	b.concat("bar", "baz");
	jum.assertEquals("test41", "foobarbaz", b.valueOf());
	b.concat([" thud", " quux"]);
	jum.assertEquals("test42", "foobarbaz thud quux", b.toString());
}

function test_Builder_appendRecurse(){
	var b = new dojo.string.Builder("foo");
	b.append(["bar", [[["baz"]]]]);
	jum.assertEquals("test41", "foobarbaz", b.valueOf());
	b.append([[" thud"], " quux"]);
	jum.assertEquals("test42", "foobarbaz thud quux", b.toString());
}

function test_Builder_concatRecurse(){
	var b = new dojo.string.Builder("foo");
	b.concat(["bar", [[["baz"]]]]);
	jum.assertEquals("test41", "foobarbaz", b.valueOf());
	b.concat([[" thud"], " quux"]);
	jum.assertEquals("test42", "foobarbaz thud quux", b.toString());
}

function test_Builder_clear(){
	var b = new dojo.string.Builder("foo");
	jum.assertEquals("test60", "foo", b.valueOf());
	jum.assertEquals("test70", "", b.clear().valueOf());
}

function test_Builder_remove(){
	var b = new dojo.string.Builder("foo ");
	b.remove(0, 3);
	jum.assertEquals("test80", " ", b.valueOf());
}

function test_Builder_replace(){
	var b = new dojo.string.Builder(" foo ");
	jum.assertEquals("test90", "bar ", b.replace(" foo", "bar").valueOf());
}

function test_Builder_insert(){
	var b = new dojo.string.Builder(" ");
	jum.assertEquals("test100", "foo ", b.insert(0, "foo").valueOf());
}

